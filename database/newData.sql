-- Catégories
INSERT INTO CATEGORIE(nom) VALUES ('MEDICAMENT'), ('HYGIENE');

-- Administrations
INSERT INTO ADMINISTRATION(nom) VALUES
('ORALE'), ('LOCALE'), ('NASALE'), ('OCULAIRE'), ('AURICULAIRE'),
('RECTALE'), ('VAGINALE'), ('INJECTABLE'), ('INHALEE');

-- Unités
INSERT INTO UNITE(nom) VALUES ('MG'), ('ML'), ('UNITE');

-- Formes
INSERT INTO FORME(nom, id_unite) VALUES
('COMPRIME', 1), ('GELULE', 1), ('SIROP', 2), ('CREME', 2), ('COLLYRE', 2);

-- Forme-administration
INSERT INTO Forme_administration (id_administration, id_forme) VALUES
(1, 1), (1, 2), (1, 3), -- Administration ORALE
(2, 4),                 -- Administration LOCALE
(4, 5);                 -- Administration OCULAIRE

-- Pays
INSERT INTO PAYS(nom) VALUES ('MADAGASCAR'), ('FRANCE'), ('INDE');

-- Laboratoires
INSERT INTO Laboratoire (nom, id_pays_origine) VALUES
('Laboratoire BioMad', 1), 
('Laboratoire Sanofi', 2), 
('Laboratoire Serum Institute', 3);

-- Types de médicaments
INSERT INTO Type_Medicament (nom) VALUES
('Antibiotique'), ('Antalgiques'), ('Vitamines');

-- Produits
INSERT INTO Produit (nom, sur_ordonnance, id_categorie) VALUES
('Paracetamol 500mg', TRUE, 1), 
('Ibuprofene 200mg', TRUE, 1), 
('Gel Hydroalcoolique', FALSE, 2);

-- Prix des produits
INSERT INTO prix_produit (date_insertion, montant, id_produit) VALUES
('2025-01-01', 5000, 1), 
('2025-01-01', 3000, 2), 
('2025-01-01', 15000, 3);

-- Médicaments
INSERT INTO Medicament (nom, dateInsertion, id_laboratoire, id_type_medicament) VALUES
('Paracetamol', '2025-01-01', 1, 2), 
('Ibuprofene', '2025-01-01', 2, 2);

-- Liaison médicaments et produits
INSERT INTO age_medicament (id_medicament, id_age) VALUES
(1, 4),  -- Paracetamol pour adulte
(2, 5);  -- Ibuprofene pour personne âgée

INSERT INTO forme_admin_medoc (id_medicament, id_forme_administration) VALUES
(1, 1),  -- Paracetamol : Comprimé, Administration Orale
(2, 2);  -- Ibuprofene : Gélule, Administration Orale

-- Hygiène
INSERT INTO Hygiene (nom, id_produit, id_laboratoire) VALUES
('Gel Hydroalcoolique', 3, 1); -- Produit lié à BioMad

-- Maladies
INSERT INTO Maladie (nom, est_contagieuse, est_grave, est_rare) VALUES
('Grippe', TRUE, FALSE, FALSE), 
('Maux de tête', FALSE, FALSE, FALSE);

-- Médicaments traitant les maladies
INSERT INTO Maladie_medicament (id_maladie, id_medicament) VALUES
(1, 1), -- Paracetamol pour Grippe
(2, 2); -- Ibuprofene pour Maux de tête

-- Mouvements de stock
INSERT INTO mvt_stock (date_insertion, date_fabrication, date_peremption, qte_entree, qte_sortie, id_produit) VALUES
('2025-01-01', '2024-12-01', '2025-12-01', 50, 0, 1), 
('2025-01-02', '2024-12-15', '2025-12-15', 30, 0, 2), 
('2025-01-03', '2024-11-01', '2025-11-01', 100, 0, 3);

-- Ventes
INSERT INTO VENTE VALUES (default, CURRENT_DATE);

-- Détails des ventes
INSERT INTO vente_detail (qte, id_vente, id_produit) VALUES
(5, 1, 1), -- Vente de Paracetamol
(3, 1, 2), -- Vente d'Ibuprofene
(10, 1, 3); -- Vente de Gel Hydroalcoolique
