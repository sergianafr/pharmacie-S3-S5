package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Medicament {
    public int id;
    public String nom;
    // public int idProduit;
    public int idLaboratoire;
    public int idTypeMedicament;
    public Date dateInsertion;
    private String nomLaboratoire; 

    private List<FormeAdministration> formesAdmin;
    private List<QuantiteMedoc> quantites;
    private List<Maladie> maladies;
    private List<Age> ages;
    private List<FormeAdminMedoc> formeAdminMedoc = new ArrayList<>();
    private boolean surOrdonnance; 
    private List<Produit> produits = new ArrayList<Produit>();

    public List<Age> getAges() {
        return ages;
    }
    public void setAges(List<Age> ages) {
        this.ages = ages;
    }
    public List<Maladie> getMaladies() {
        return maladies;
    }
    public void setMaladies(List<Maladie> maladies) {
        this.maladies = maladies;
    }

    public List<QuantiteMedoc> getQuantites() {
        return quantites;
    }

    public int getIdTypeMedicament() {
        return idTypeMedicament;
    }public List<Produit> getProduits() {
        return produits;
    }
    public void setIdTypeMedicament(int idTypeMedicament) {
        this.idTypeMedicament = idTypeMedicament;
    }public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }public void setSurOrdonnance(boolean surOrdonnance) {
        this.surOrdonnance = surOrdonnance;
    }
    
    public void setQuantites(List<QuantiteMedoc> quantites) {
        this.quantites = quantites;
    }
    public void setFormesAdmin(List<FormeAdministration> formesAdmin) {
        this.formesAdmin = formesAdmin;
    }
    public List<FormeAdministration> getFormesAdmin() {
        return formesAdmin;
    }
    public void setFormeAdminMedoc(List<FormeAdminMedoc> formeAdminMedoc) {
        this.formeAdminMedoc = formeAdminMedoc;
    }
    public List<FormeAdminMedoc> getFormeAdminMedoc() {
        return formeAdminMedoc;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setIdLaboratoire(int idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }public int getIdLaboratoire() {
        return idLaboratoire;
    }public String getNom() {
        return nom;
    }
    public Date getDateInsertion() {
        return dateInsertion;
    }
    public void setDateInsertion(Date dateInsertion) {
        this.dateInsertion = dateInsertion;
    }
    public void setNomLaboratoire(String nomLaboratoire) {
        this.nomLaboratoire = nomLaboratoire;
    }
    public String getNomLaboratoire() {
        return nomLaboratoire;
    }
    public Medicament(int id, String nom, Date dateInsertion, int idLaboratoire) {
        this.id = id;
        this.nom = nom;
        this.dateInsertion = dateInsertion;
        // this.idProduit = idProduit;
        this.idLaboratoire = idLaboratoire;
    }
    public Medicament(){}

    public static List<Medicament> getWithDetails(Connect c) throws Exception { 
        try {
            StringBuilder sql = new StringBuilder(
                "SELECT m.*, l.nom as nomLaboratoire "+
                "FROM medicament m JOIN Laboratoire l ON m.id_Laboratoire=l.id "
            );
            // String sql = "SELECT Medicament.*, Laboratoire.nom as nomLaboratoire FROM Medicament JOIN Laboratoire ON Medicament.id_Laboratoire=Laboratoire.id";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql.toString());
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Medicament> results = new ArrayList<Medicament>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Date dateInsertion = rs.getDate(3);
                int idLaboratoire = rs.getInt(4);
                String nomLaboratoire = rs.getString(6);
                Medicament medicament = new Medicament(id, nom, dateInsertion, idLaboratoire);
                medicament.setNomLaboratoire(nomLaboratoire);
                results.add(medicament);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // c.closeBD();
            throw e;
        }
    }

    public static List<Medicament> getMedicaments(Connect co, Integer idMaladie, Integer idAge) throws SQLException {
        List<Medicament> results = new ArrayList<>();
        StringBuilder query = new StringBuilder(
            "SELECT DISTINCT m.*, l.nom " +
            "FROM Medicament m " +
            "JOIN Laboratoire l ON m.id_Laboratoire=l.id "+
            "LEFT JOIN age_medicament am ON m.id = am.id_medicament " +
            "LEFT JOIN Maladie_medicament mm ON m.id = mm.id_medicament " +
            "WHERE 1=1 "
        );


        if (idMaladie != 0) {
            query.append("AND mm.id_maladie = ? ");
        }
        if (idAge != 0) {
            query.append("AND am.id_age = ? ");
        }

        try (PreparedStatement stmt = co.getConnex().prepareStatement(query.toString())) {
        //    System.out.println(stmt.toString());
            int i = 1;

            if (idMaladie != 0) {
                stmt.setInt(i++, idMaladie);
            }
            if (idAge != 0) {
                stmt.setInt(i++, idAge);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    // System.out.println(rs);
                    int id = rs.getInt(1);
                    String nom = rs.getString(2);
                    Date dateInsertion = rs.getDate(3);
                    int idLaboratoire = rs.getInt(4);
                    String nomLaboratoire = rs.getString(6);
                    Medicament medicament = new Medicament(id, nom, dateInsertion, idLaboratoire);
                    medicament.setNomLaboratoire(nomLaboratoire);
                    results.add(medicament);   
                }
            }
        }

        return results;
    }

    public void insertReturnId(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO Medicament VALUES (default , ?, current_date, ?, 1 )";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.getNom());
            preparedStatement.setInt(2, this.getIdLaboratoire());

            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1); 
            }

            generatedKeys.close();
            preparedStatement.close();

            c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }


    /* TO DO: creation medicament
     * age medicament
     * maladie_medoc
     * forme admin medoc
     */
    public void insertMaladie(Connect c)throws Exception {
        try {
            for (Maladie maladie : this.getMaladies()) {
                MaladieMedicament mm = new MaladieMedicament(0, maladie.getId(), this.getId());
                mm.create(c);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void insertAge(Connect connect)throws Exception {
        try {
            for (Age age : ages) {
                AgeMedicament am = new AgeMedicament(0, this.getId(), age.getId());
                am.create(connect);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void insertFormeAdmin(Connect connect)throws Exception {
        try {
            for (FormeAdministration fa : formesAdmin) {
                FormeAdminMedoc fam = new FormeAdminMedoc(0, 0, 0, this.getId());
                fam.setIdFormeAdmin(fa.getId());
                fam.create(connect);
                this.formeAdminMedoc.add(fam);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void insertProduit(Connect connect)throws Exception {
        try {
            int i = 0;
            for (FormeAdminMedoc fam : formeAdminMedoc) {
                String nomProduit = fam.buildNomProduit()+quantites.get(i).buildNomProduit();
                i++;
                Produit p = new Produit(0, nomProduit, surOrdonnance, 1);
                p.create(connect);
                produits.add(p);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    public void insertQuantiteMedoc(Connect connect)throws Exception {
        try {
            int i = 0;
            for (QuantiteMedoc qte : quantites) {
                qte.setIdProduit(produits.get(i).getId());
                qte.setIdFormeAdminMedoc(formeAdminMedoc.get(i).getId());
                i++;
                qte.create(connect);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void validerInsert(Connect c)throws Exception {
        try {
            this.insertReturnId(c);
            this.insertAge(c);
            this.insertMaladie(c);
            this.insertFormeAdmin(c);
            insertProduit(c);
            insertQuantiteMedoc(c);
        } catch (Exception e) {
            throw e;
        }
    }
    
}
