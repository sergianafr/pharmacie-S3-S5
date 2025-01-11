CREATE OR REPLACE VIEW v_etat_stock AS
SELECT id_produit, date_peremption, sum(qte_entree-qte_sortie) as qte_dispo
from mvt_stock 
group by id_produit, date_peremption;


