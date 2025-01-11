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

-- Insertion des produits dans la table Produit
INSERT INTO Produit (nom, sur_ordonnance, id_categorie) VALUES
('Amoxicilline', TRUE, 1),  -- Catégorie 1: Antibiotique
('Paracétamol', TRUE, 1),  -- Catégorie 2: Analgésique
('Insuline', TRUE, 1);  -- Catégorie 3: Médicaments pour diabète
-- Insertion des médicaments dans la table Medicament
INSERT INTO Medicament (nom, dateInsertion, id_laboratoire, id_type_medicament) VALUES
('Amoxicilline', '2025-01-06',  1, 1),  -- Madagascar, Antibiotique
('Doliprane', '2025-01-06', 2, 2),  -- France, Antalgiques
('Insuline', '2025-01-06',  3, 10);  -- Inde, Antidiabétiques

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

INSERT INTO Age (nom) VALUES
('Nouveau-ne & bebe'),
('Enfant'),
('Adolescent'),
('Adulte'),
('Personne agee');

INSERT INTO Maladie_medicament (id_maladie, id_medicament) VALUES

(1, 2), -- Grippe traitée par Doliprane
(2, 1), -- Tuberculose traitée par Amoxicilline
(3, 3), -- Diabète traité par Insuline
(4, 2), -- Varicelle traitée par Doliprane
(5, 1), -- Cancer du poumon traité par Amoxicilline
(6, 3), -- Sclérose en plaques traitée par Insuline
(7, 1), -- Ebola traité par Amoxicilline
(8, 3), -- Paludisme traité par Insuline
(9, 2), -- Migraine traitée par Doliprane
(10, 2); -- Anémie traitée par Doliprane

INSERT INTO age_maladie (id_maladie, id_age) VALUES
(1, 3), -- Grippe fréquente chez les adolescents
(2, 4), -- Tuberculose fréquente chez les adultes
(3, 4), -- Diabète fréquent chez les adultes
(4, 2), -- Varicelle fréquente chez les enfants
(5, 5), -- Cancer du poumon fréquent chez les personnes âgées
(6, 4), -- Sclérose en plaques fréquente chez les adultes
(7, 4), -- Ebola fréquent chez les adultes
(8, 2), -- Paludisme fréquent chez les enfants
(9, 3), -- Migraine fréquente chez les adolescents
(10, 4); -- Anémie fréquente chez les adultes

INSERT INTO age_medicament (id_medicament, id_age) VALUES 
(1, 2), -- Enfant
(1, 4), -- Adulte
(1, 1);

INSERT INTO age_medicament (id_medicament, id_age) VALUES 
-- Doliprane (id_medicament = 2) est adapté aux nouveau-nés, enfants, et adultes
(2, 1), -- Nouveau-né & bébé
(2, 2), -- Enfant
(2, 4); -- Adulte
INSERT INTO age_medicament (id_medicament, id_age) VALUES 
-- Insuline (id_medicament = 3) est adapté aux adolescents, adultes, et personnes âgées
(3, 3), -- Adolescent
(3, 4), -- Adulte
(3, 5); -- Personne âgée

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

