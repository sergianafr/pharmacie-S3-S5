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


CREATE OR REPLACE FUNCTION filtre_w_age(id_forme_input INTEGER, id_age_input INTEGER, id_client_inp INTEGER, date_v DATE)
RETURNS TABLE(
    id INTEGER,
    qte DOUBLE PRECISION,
    pu DOUBLE PRECISION,
    prix_total DOUBLE PRECISION,
    id_vente INTEGER,
    id_produit INTEGER,
    nom_produit VARCHAR,
    date_vente DATE,
    client VARCHAR(100)
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
        v.date_vente,
        cl.nom as client
    FROM vente_detail vd
    JOIN produit p ON vd.id_produit = p.id
    JOIN vente v ON vd.id_vente = v.id
    JOIN client cl on cl.id = v.id_client
    JOIN quantite_medoc qm ON qm.id_produit = p.id
    JOIN forme_admin_medoc fam ON fam.id = qm.id_forme_admin_medoc
    JOIN forme_administration fa on fam.id_forme_administration = fa.id
    JOIN forme form on form.id = fa.id_forme
    JOIN administration admins on admins.id = fa.id_administration  
    JOIN age_medicament am ON am.id_medicament = fam.id_medicament
    WHERE 
        p.id_categorie = 1 
        AND (id_forme_input = 0 OR form.id = id_forme_input) 
        AND (id_age_input = 0 OR am.id_age = id_age_input)
        AND (id_client_inp = 0 OR v.id_client = id_client_inp)
        AND (date_v = null OR v.date_vente = date_v);
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION filtrer_ventes(
    p_id_administration INTEGER DEFAULT NULL,
    p_id_age INTEGER DEFAULT NULL,
    p_id_client INTEGER DEFAULT NULL,
    p_date_vente DATE DEFAULT NULL
)
RETURNS TABLE (
    id_vente INTEGER,
    date_vente DATE,
    id_client INTEGER,
    client_nom VARCHAR,
    id_produit INTEGER,
    produit_nom VARCHAR,
    qte DOUBLE PRECISION,
    pu DOUBLE PRECISION,
    prix_total DOUBLE PRECISION
) AS $$
BEGIN
    RETURN QUERY
    SELECT
        v.id AS id_vente,
        v.date_vente,
        v.id_client,
        c.nom AS client_nom,
        vd.id_produit,
        p.nom AS produit_nom,
        vd.qte,
        vd.pu,
        vd.prix_total
    FROM
        vente v
    JOIN vente_detail vd ON v.id = vd.id_vente
    JOIN client c ON v.id_client = c.id
    JOIN produit p ON vd.id_produit = p.id
    LEFT JOIN medicament m ON m.id = p.id
    LEFT JOIN forme_admin_medoc fam ON fam.id_medicament = m.id
    LEFT JOIN forme_administration fa ON fa.id = fam.id_forme_administration
    LEFT JOIN age_medicament am ON am.id_medicament = m.id
    WHERE
        (p_id_administration IS NULL OR fa.id_administration = p_id_administration)
        AND (p_id_age IS NULL OR am.id_age = p_id_age)
        AND (p_id_client IS NULL OR v.id_client = p_id_client)
        AND (p_date_vente IS NULL OR v.date_vente = p_date_vente);
END;
$$ LANGUAGE plpgsql;

