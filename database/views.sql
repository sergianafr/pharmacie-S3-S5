CREATE OR REPLACE VIEW v_etat_stock AS
SELECT id_produit, date_peremption, sum(qte_entree-qte_sortie) as qte_dispo
from mvt_stock 
group by id_produit, date_peremption;

CREATE OR REPLACE VIEW v_details_produit AS
 SELECT p.*, Categorie.nom as nom_categorie, pp.montant
            FROM Produit p
            JOIN prix_produit pp ON p.id = pp.id_produit
            JOIN categorie on p.id_categorie = categorie.id
            WHERE pp.date_insertion = (
                SELECT MAX(date_insertion)
                FROM prix_produit
                WHERE id_produit = p.id
            );