SELECT DISTINCT m.nom AS nom_medicament
FROM Medicament m
INNER JOIN age_medicament am ON m.id = am.id_medicament
INNER JOIN Maladie_medicament mm ON m.id = mm.id_medicament
INNER JOIN Maladie ma ON mm.id_maladie = ma.id
INNER JOIN Age a ON am.id_age = a.id
WHERE ma.nom = 'nom_de_la_maladie'
  AND a.nom = 'nom_de_la_tranche_d_age';

--- Age par medicament
SELECT 
    a.id AS id_age,
    a.nom AS nom_tranche_age
FROM age_medicament am
INNER JOIN Age a ON am.id_age = a.id
WHERE am.id_medicament = :idMedicament;

-- maladie par medicament
SELECT 
    m.id AS id_maladie,
    m.nom AS nom_maladie,
    m.est_contagieuse,
    m.est_grave,
    m.est_rare
FROM Maladie_medicament mm
INNER JOIN Maladie m ON mm.id_maladie = m.id
WHERE mm.id_medicament = :idMedicament;
