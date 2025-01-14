INSERT INTO CATEGORIE(nom) VALUES ('MEDICAMENT'), ('HYGIENE');
INSERT INTO ADMINISTRATION(nom) VALUES
('ORALE'),('LOCALE'),('NASALE'),('OCULAIRE'),('AURICULAIRE'),
('RECTALE'),('VAGINALE'),('INJECTABLE'),('INHALEE');

INSERT INTO UNITE(nom) VALUES
('MG'), ('ML'), ('UNITE');

INSERT INTO FORME(nom, id_unite) VALUES
('COMPRIME', 1), ('GELULE', 1), ('SIROP', 2),
('GOUTTE', 2), ('SUSPENSION BUVABLE', 2),('POMMADE', 2),
('CREME', 2), ('GEL', 2), ('SOLUTION', 2),
('POUDRE', 1), ('PATCH', 3), ('COLLYRE', 2),
('SUPPOSITOIRE', 3), ('LAVEMENT', 2), ('OVULE', 1),
('CAPSULE', 1), ('AMPOULE', 2), ('AEROSOL', 2);

INSERT INTO Forme_administration (id_administration, id_forme) VALUES
-- Administration ORALE
(1, 1),  -- COMPRIME
(1, 2),  -- GELULE
(1, 3),  -- SIROP
(1, 4),  -- GOUTTE
(1, 5),  -- SUSPENSION BUVABLE
(1, 16), -- CAPSULE

-- Administration LOCALE
(2, 6),  -- POMMADE
(2, 7),  -- CREME
(2, 8),  -- GEL
(2, 9),  -- SOLUTION
(2, 12), -- COLLYRE
(2, 14), -- LAVEMENT

-- Administration NASALE
(3, 9),  -- SOLUTION
(3, 17), -- AMPOULE
(3, 18), -- AEROSOL

-- Administration OCULAIRE
(4, 12), -- COLLYRE
(4, 9),  -- SOLUTION

-- Administration AURICULAIRE
(5, 9),  -- SOLUTION

-- Administration RECTALE
(6, 13), -- SUPPOSITOIRE
(6, 14), -- LAVEMENT

-- Administration VAGINALE
(7, 15), -- OVULE

-- Administration INJECTABLE
(8, 17), -- AMPOULE
(8, 10), -- POUDRE (après reconstitution)

-- Administration INHALEE
(9, 18); -- AEROSOL


INSERT INTO Age (nom) VALUES
('Nouveau-ne & bebe'),
('Enfant'),
('Adolescent'),
('Adulte'),
('Personne agee');
INSERT INTO PAYS(nom) VALUES ('MADAGASCAR'),('FRANCE'), ('INDE');

INSERT INTO Laboratoire (nom, id_pays_origine) VALUES
('Laboratoire BioMad', 1),  -- Madagascar
('Laboratoire Sanofi', 2),  -- France
('Laboratoire Serum Institute', 3);  -- Inde

-- Insertion des types de médicaments dans la table Type_Medicament
INSERT INTO Type_Medicament (nom) VALUES
('Antibiotique'),
('Antalgiques'),
('Antiviraux'),
('Anti-inflammatoires'),
('Antifongiques'),
('Anxiolytiques'),
('Antipyretiques'),
('Antiseptiques'),
('Vitamines'),
('Antidiabétiques');

INSERT INTO Produit (nom, sur_ordonnance, id_categorie) VALUES
('Paracétamol', TRUE, 1),  -- Catégorie MEDICAMENT
('Gel désinfectant', FALSE, 2),  -- Catégorie HYGIENE
('Sirop pour la toux', TRUE, 1),
('Savon antiseptique', FALSE, 2);

INSERT INTO prix_produit (date_insertion, montant, id_produit) VALUES
('2025-01-01', 5000, 1), 
('2025-01-01', 15000, 2),
('2025-01-01', 8000, 3),
('2025-01-01', 3000, 4);

INSERT INTO age_produit (id_produit, id_age) VALUES
(1, 4),  -- Paracétamol pour adulte
(1, 5),  -- Paracétamol pour personne âgée
(2, 2),  -- Gel désinfectant pour enfant
(3, 3),  -- Sirop pour adolescent
(4, 2);  -- Savon antiseptique pour enfant

INSERT INTO Hygiene (nom, id_produit, id_laboratoire) VALUES
('Gel Hydroalcoolique', 2, 1),  -- Produit lié à BioMad
('Savon Désinfectant', 4, 2);  -- Produit lié à Sanofi

INSERT INTO Medicament (nom, dateInsertion, id_laboratoire, id_type_medicament) VALUES
('Ibuprofène', '2025-01-01', 2, 4),  -- Anti-inflammatoire, Sanofi
('Amoxicilline', '2025-01-01', 3, 1),  -- Antibiotique, Serum Institute
('Vitamine C', '2025-01-01', 1, 9);  -- Vitamine, BioMad

INSERT INTO age_medicament (id_medicament, id_age) VALUES
(1, 4),  -- Ibuprofène pour adulte
(2, 3),  -- Amoxicilline pour adolescent
(3, 5);  -- Vitamine C pour personne âgée

INSERT INTO forme_admin_medoc (id_medicament, id_forme_administration) VALUES
(1, 1),  -- Ibuprofène : Comprimé, Administration Orale
(2, 2),  -- Amoxicilline : Gélule, Administration Orale
(3, 3);  -- Vitamine C : Sirop, Administration Orale

INSERT INTO quantite_medoc (qte, id_unite, id_forme_admin_medoc, id_produit) VALUES
(500, 1, 1, 1),  -- Ibuprofène, 500 mg
(250, 1, 2, 3),  -- Amoxicilline, 250 mg
(100, 2, 3, 3);  -- Vitamine C, 100 ml

INSERT INTO Maladie (nom, est_contagieuse, est_grave, est_rare) VALUES
('Grippe', TRUE, FALSE, FALSE),
('Maux de gorge', TRUE, TRUE, FALSE),
('Diabète', FALSE, TRUE, FALSE),
('Varicelle', TRUE, FALSE, FALSE),
('Cancer du poumon', FALSE, TRUE, FALSE),
('Sclérose en plaques', FALSE, TRUE, TRUE),
('Ebola', TRUE, TRUE, TRUE),
('Paludisme', TRUE, TRUE, FALSE),
('Migraine', FALSE, FALSE, FALSE),
('Anémie', FALSE, FALSE, FALSE);

INSERT INTO Maladie_medicament (id_maladie, id_medicament) VALUES
(1, 1),  -- Ibuprofène pour Grippe
(2, 2),  -- Amoxicilline pour Maux de gorge
(9, 3);  -- Vitamine C pour Migraine


INSERT INTO mvt_stock (date_insertion, date_fabrication, date_peremption, qte_entree, qte_sortie, id_produit) VALUES
('2025-01-01', '2024-12-01', '2025-12-01', 20, 0, 1), -- Produit 1
('2025-01-02', '2024-11-15', '2025-11-15', 10, 0, 2), -- Produit 2
('2025-01-03', '2024-12-10', '2025-06-10', 20, 0, 3), -- Produit 3
('2025-01-04', '2024-10-01', '2025-10-01', 20, 0, 4); -- Produit 4

INSERT INTO VENTE VALUES (default, CURRENT_DATE);

INSERT INTO vente_detail (qte, id_vente, id_produit) VALUES
(5,  1, 1), -- Vente 1, Produit 1
(5,  1, 2),  -- Vente 1, Produit 2
(7,1, 3);-- Vente 2, Produit 3
