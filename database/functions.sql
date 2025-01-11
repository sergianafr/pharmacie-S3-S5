CREATE OR REPLACE FUNCTION filter_ventedetail_by_forme_age(id_forme_input INTEGER, id_age_input INTEGER)
RETURNS TABLE(
    id INTEGER,
    qte DOUBLE PRECISION,
    pu DOUBLE PRECISION,
    prix_total DOUBLE PRECISION,
    id_vente INTEGER,
    id_produit INTEGER,
    nom_produit VARCHAR,
    date_vente DATE
) AS $$
BEGIN
    RETURN QUERY
    SELECT 
        vd.id,
        vd.qte,
        vd.pu,
        vd.prix_total,
        vd.id_vente,
        vd.id_produit,
        p.nom AS nom_produit,
        v.date_vente
    FROM vente_detail vd
    JOIN produit p ON vd.id_produit = p.id
    JOIN vente v ON vd.id_vente = v.id
    JOIN quantite_medoc qm ON qm.id_produit = p.id
    JOIN forme_admin_medoc fam ON fam.id = qm.id_forme_admin_medoc
    JOIN age_medicament am ON am.id_medicament = fam.id_medicament
    WHERE 
        p.id_categorie = 1 -- Vérifie que le produit appartient à la catégorie 1
        AND (id_forme_input = 0 OR fam.id_forme = id_forme_input) -- Si id_forme_input est 0, ne pas filtrer par forme
        AND (id_age_input = 0 OR am.id_age = id_age_input); -- Si id_age_input est 0, ne pas filtrer par âge
END;
$$ LANGUAGE plpgsql;
