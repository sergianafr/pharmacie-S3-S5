\c postgres 
drop database pharmacie;
CREATE DATABASE pharmacie
WITH ENCODING 'UTF8'
LC_COLLATE='fr_FR.UTF-8'
LC_CTYPE='fr_FR.UTF-8'
TEMPLATE template0;

\c pharmacie
CREATE TABLE Administration(
   id SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(id),
   UNIQUE(nom)
);
CREATE TABLE Pays(
   id SERIAL,
   nom VARCHAR(50) ,
   PRIMARY KEY(id)
);
CREATE TABLE unite(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE Systeme_cible(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);
CREATE TABLE Duree(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE Age(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE Maladie(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   est_contagieuse BOOLEAN NOT NULL,
   est_grave BOOLEAN NOT NULL,
   est_rare BOOLEAN NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);
CREATE TABLE duree_maladie(
   id SERIAL,
   id_duree INTEGER NOT NULL,
   id_maladie INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_duree) REFERENCES Duree(id),
   FOREIGN KEY(id_maladie) REFERENCES Maladie(id)
);
CREATE TABLE age_maladie(
   id SERIAL,
   id_maladie INTEGER NOT NULL,
   id_age INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_maladie) REFERENCES Maladie(id),
   FOREIGN KEY(id_age) REFERENCES Age(id)
);
CREATE TABLE systeme_cible_maladie(
   id SERIAL,
   id_systeme_cible INTEGER NOT NULL,
   id_maladie INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_systeme_cible) REFERENCES Systeme_cible(id),
   FOREIGN KEY(id_maladie) REFERENCES Maladie(id)
);
CREATE TABLE Categorie(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom)
);

CREATE TABLE Forme(
   id SERIAL,
   nom VARCHAR(50) ,
   id_unite INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom),
   FOREIGN KEY(id_unite) REFERENCES unite(id)
);
CREATE TABLE Laboratoire(
   id SERIAL,
   nom VARCHAR(50) ,
   id_pays_origine INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_pays_origine) REFERENCES Pays(id)
);
CREATE TABLE Forme_administration(
   id SERIAL,
   id_administration INTEGER NOT NULL,
   id_forme INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_administration) REFERENCES Administration(id),
   FOREIGN KEY(id_forme) REFERENCES Forme(id)
);
CREATE TABLE Produit(
   id SERIAL,
   nom VARCHAR(100)  NOT NULL,
   sur_ordonnance BOOLEAN NOT NULL,
   id_categorie INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_categorie) REFERENCES Categorie(id)
);
CREATE TABLE age_produit(
   id SERIAL, 
   id_produit INTEGER NOT NULL,
   id_age INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_produit) REFERENCES Produit(id),
   FOREIGN KEY(id_age) REFERENCES Age(id)
);
CREATE TABLE Hygiene(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   id_produit INTEGER NOT NULL,
   id_laboratoire INTEGER NOT NULL,
   PRIMARY KEY(id),
   UNIQUE(nom),
   FOREIGN KEY(id_produit) REFERENCES Produit(id),
   FOREIGN KEY(id_laboratoire) REFERENCES Laboratoire(id)
);
CREATE TABLE Type_Medicament (
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE Medicament(
   id SERIAL,
   nom VARCHAR(50)  NOT NULL,
   dateInsertion DATE NOT NULL,
   id_laboratoire INTEGER,
   id_type_medicament INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_laboratoire) REFERENCES Laboratoire(id),
   FOREIGN KEY(id_type_medicament) REFERENCES Type_medicament(id)
);
-- CREATE TABLE age_medicament(
--    id SERIAL, 
--    id_medicament INTEGER NOT NULL,
--    id_age INTEGER NOT NULL,
--    PRIMARY KEY(id),
--    FOREIGN KEY(id_medicament) REFERENCES Medicament(id),
--    FOREIGN KEY(id_age) REFERENCES Age(id)
-- );
CREATE TABLE forme_admin_medoc(
   id SERIAL,
   id_medicament INTEGER NOT NULL,
   id_forme INTEGER,
   id_administration INTEGER,
   id_forme_administration INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_medicament) REFERENCES Medicament(id),
   FOREIGN KEY(id_forme) REFERENCES Forme(id),
   FOREIGN KEY(id_administration) REFERENCES Administration(id),
   FOREIGN KEY(id_forme_administration) REFERENCES Forme_administration(id)
);
CREATE TABLE quantite_medoc(
   id SERIAL,
   qte NUMERIC(15,2)   NOT NULL,
   id_unite INTEGER NOT NULL,
   id_forme_admin_medoc INTEGER NOT NULL,
   id_produit INTEGER NOT NULL,
   PRIMARY KEY(id),
   FOREIGN KEY(id_forme_admin_medoc) REFERENCES forme_admin_medoc(id),
   FOREIGN KEY(id_produit) REFERENCES Produit(id),
   FOREIGN KEY(id_unite) REFERENCES unite(id)
);
CREATE TABLE Maladie_medicament(
   id SERIAL ,
   id_maladie INTEGER,
   id_medicament INTEGER,
   PRIMARY KEY(id),
   FOREIGN KEY(id_maladie) REFERENCES Maladie(id),
   FOREIGN KEY(id_medicament) REFERENCES Medicament(id)
);

CREATE TABLE mvt_stock(
   id serial primary key, 
   date_insertion date,
   date_fabrication date,
   date_peremption date,
   qte_entree double precision,
   qte_sortie double precision,
   id_produit integer references produit(id)
);

CREATE TABLE vente (
   id serial primary key,
   date_vente date
);

CREATE TABLE vente_detail(
   id serial primary key,
   qte double precision,
   pu double precision,
   prix_total double precision,
   id_vente integer references vente(id),
   id_produit integer references Produit(id)
);

CREATE TABLE prix_produit(
   id serial primary key,
   date_insertion date,
   montant double precision,
   id_produit integer references Produit(id)
);

