-- Fonction déclencheur modifiée pour calculer le prix_total et définir le prix unitaire (pu)
CREATE OR REPLACE FUNCTION calculate_prix_total()
RETURNS TRIGGER AS $$
DECLARE
    latest_pu double precision;
BEGIN
    -- Récupérer le prix unitaire le plus récent depuis la table prix_produit
    SELECT montant
    INTO latest_pu
    FROM prix_produit
    WHERE id_produit = NEW.id_produit
    ORDER BY date_insertion DESC
    LIMIT 1;

    -- Si aucun prix n'est trouvé, lever une exception
    IF latest_pu IS NULL THEN
        RAISE EXCEPTION 'Aucun prix trouvé pour le produit %', NEW.id_produit;
    END IF;

    -- Mettre à jour le prix unitaire (pu) et calculer le prix_total
    NEW.pu := latest_pu;
    NEW.prix_total := NEW.qte * NEW.pu;

    -- Mettre à jour la commission de l'employé
    IF(NEW.prix_total > 200000) THEN
        UPDATE VENTE 
        SET comission_employe = comission_employe + (NEW.prix_total * 0.05)
        WHERE id = NEW.id_vente;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du trigger sur la table vente_detail
CREATE TRIGGER trg_calculate_prix_total
BEFORE INSERT ON vente_detail
FOR EACH ROW
EXECUTE FUNCTION calculate_prix_total();



-- Fonction déclencheur pour vérifier la disponibilité du produit en stock
CREATE OR REPLACE FUNCTION check_stock_availability()
RETURNS TRIGGER AS $$
DECLARE
    qte_dispo double precision;
BEGIN
    -- Récupérer la quantité disponible pour le produit et la date de péremption
    SELECT sum(v.qte_dispo)
    INTO qte_dispo
    FROM v_etat_stock v
    WHERE v.id_produit = NEW.id_produit
      AND v.date_peremption >= CURRENT_DATE; -- Ne considérer que les produits non périmés

    -- Si la quantité demandée dépasse la quantité disponible, lever une exception
    IF qte_dispo IS NULL OR qte_dispo < NEW.qte THEN
        RAISE EXCEPTION 'Quantite insuffisante pour le produit % (Quantite demanee: %, Quantite disponible: %)',
            NEW.id_produit, NEW.qte, COALESCE(qte_dispo, 0);
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


-- Création du trigger sur la table vente_detail
CREATE TRIGGER trg_check_stock_availability
BEFORE INSERT ON vente_detail
FOR EACH ROW
EXECUTE FUNCTION check_stock_availability();


-- Fonction pour gérer l'insertion dans mvt_stock
CREATE OR REPLACE FUNCTION trigger_vente_detail_insert()
RETURNS TRIGGER AS $$
DECLARE
    remaining_qte DOUBLE PRECISION;
    qte_to_deduct DOUBLE PRECISION;
    stock_record RECORD;
BEGIN
    -- Quantité à déduire de mvt_stock
    remaining_qte := NEW.qte;

    -- Boucle sur les stocks des produits avec la date de péremption la plus proche
    FOR stock_record IN
        SELECT *
        FROM mvt_stock
        WHERE id_produit = NEW.id_produit AND qte_entree > qte_sortie
        ORDER BY date_peremption ASC
    LOOP
        -- Calculer la quantité à déduire
        qte_to_deduct := LEAST(remaining_qte, stock_record.qte_entree - stock_record.qte_sortie);

        -- Insérer une sortie dans mvt_stock
        INSERT INTO mvt_stock (
            date_insertion, date_fabrication, date_peremption, qte_entree, qte_sortie, id_produit
        ) VALUES (
            CURRENT_DATE, 
            stock_record.date_fabrication, 
            stock_record.date_peremption, 
            0, 
            qte_to_deduct, 
            NEW.id_produit
        );

        -- Réduire la quantité restante
        remaining_qte := remaining_qte - qte_to_deduct;

        -- Arrêter la boucle si la quantité est totalement déduite
        IF remaining_qte <= 0 THEN
            EXIT;
        END IF;
    END LOOP;

    -- Vérifier si la quantité restante est positive après la boucle
    IF remaining_qte > 0 THEN
        RAISE EXCEPTION 'Stock insuffisant pour le produit ID %', NEW.id_produit;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Trigger sur l'insertion dans vente_detail
CREATE TRIGGER trg_vente_detail_insert
AFTER INSERT ON vente_detail
FOR EACH ROW
EXECUTE FUNCTION trigger_vente_detail_insert();


CREATE OR REPLACE FUNCTION set_date_fin()
RETURNS TRIGGER AS $$
BEGIN
   -- Calculer la date_fin en ajoutant 1 mois à date_debut
   NEW.date_fin := NEW.date_debut + INTERVAL '1 month';
   RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_set_date_fin
BEFORE INSERT ON conseil_mois
FOR EACH ROW
EXECUTE FUNCTION set_date_fin();


CREATE OR REPLACE FUNCTION check_unique_id_produit_per_month()
RETURNS TRIGGER AS $$
BEGIN
   -- Vérifier si un `id_produit` existe déjà dans le même mois
   IF EXISTS (
      SELECT 1
      FROM conseil_mois
      WHERE id_produit = NEW.id_produit
        AND date_trunc('month', date_debut) = date_trunc('month', NEW.date_debut)
   ) THEN
      RAISE EXCEPTION 'Le produit % existe deja pour ce mois.', NEW.id_produit;
   END IF;

   RETURN NEW;
END;
$$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_check_id_produit_per_month
BEFORE INSERT ON conseil_mois
FOR EACH ROW
EXECUTE FUNCTION check_unique_id_produit_per_month();
