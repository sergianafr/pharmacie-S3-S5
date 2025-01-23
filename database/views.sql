CREATE OR REPLACE VIEW v_etat_stock AS
SELECT 
id_produit, produit.nom, date_peremption, sum(qte_entree) as qte_entree, sum(qte_sortie) as qte_sortie, sum(qte_entree-qte_sortie) as qte_dispo
from mvt_stock
join produit on produit.id = mvt_stock.id_produit 
group by id_produit, nom, date_peremption
HAVING date_peremption >= CURRENT_DATE;

CREATE OR REPLACE VIEW v_details_produit AS
 SELECT p.*, Categorie.nom as nom_categorie, Client.nom as client, pp.montant
            FROM Produit p
            JOIN prix_produit pp ON p.id = pp.id_produit
            JOIN categorie on p.id_categorie = categorie.id
            WHERE pp.date_insertion = (
                SELECT MAX(date_insertion)
                FROM prix_produit
                WHERE id_produit = p.id
            );

CREATE OR REPLACE VIEW v_details_laboratoire AS
SELECT l.*, p.nom as pays from Laboratoire l
join pays p on l.id_pays_origine = p.id;

CREATE OR REPLACE VIEW v_details_conseil AS
 SELECT p.*, Categorie.nom as nom_categorie, pp.montant, cm.date_debut, cm.date_fin
FROM Produit p
JOIN prix_produit pp ON p.id = pp.id_produit
JOIN categorie on p.id_categorie = categorie.id
JOIN conseil_mois cm on p.id = cm.id_produit
WHERE pp.date_insertion = (
    SELECT MAX(date_insertion)
    FROM prix_produit
    WHERE id_produit = p.id
);


CREATE OR REPLACE VIEW V_commission_employe AS 
SELECT Vente.*, EMploye.nom, sum(vente_detail.prix_total) as total_vente
FROM Vente 
JOIN vente_detail on vente_detail.id_vente = vente.id
JOIN EMploye on employe.id = vente.id_employe
GROUP BY vente.id, vente.date_vente, employe.nom;

-- AND cm.date_fin >= CURRENT_DATE AND cm.date_debut<= CURRENT_DATE