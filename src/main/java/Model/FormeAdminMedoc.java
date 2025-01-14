package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class FormeAdminMedoc {
    public int id;
    public int idForme;
    public int idAdministration;
    public int idMedicament;
    public int idFormeAdmin;
    private String nomForme;
    private String nomAdministration;
    private String nomMedicament;


    public int getIdFormeAdmin() {
        return idFormeAdmin;
    }
    public String getNomAdministration() {
        return nomAdministration;
    }public String getNomForme() {
        return nomForme;
    }
    public void setIdFormeAdmin(int idFormeAdmin) {
        this.idFormeAdmin = idFormeAdmin;
    }public void setNomAdministration(String nomAdministration) {
        this.nomAdministration = nomAdministration;
    }public void setNomForme(String nomForme) {
        this.nomForme = nomForme;
    }
    public void setId(int id) {
        this.id = id;
    }public void setIdForme(int idForme) {
        this.idForme = idForme;
    }public void setIdAdministration(int idAdministration) {
        this.idAdministration = idAdministration;
    }public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public int getId() {
        return id;
    }public int getIdForme() {
        return idForme;
    }public int getIdAdministration() {
        return idAdministration;
    }public int getIdMedicament() {
        return idMedicament;
    }

    public FormeAdminMedoc(int id, int idForme, int idAdministration, int idMedicament) {
        this.id = id;
        this.idForme = idForme;
        this.idAdministration = idAdministration;
        this.idMedicament = idMedicament;
    }

    public void getById(Connect c)throws Exception{
        try{
            String sql = """
                SELECT 
                    fam.*,
                    a.nom as nom_administration, f.nom as nom_forme, m.nom as nom_medicament
                FROM 
                    forme_admin_medoc fam
                JOIN 
                    Forme_administration fa ON fam.id_forme_administration = fa.id
                JOIN 
                    Forme f ON fa.id_forme = f.id
                JOIN 
                    Administration a ON fa.id_administration = a.id
                JOIN 
                    Medicament m ON fam.id_medicament = m.id;
                """;

            // String sql = "select fam.*, a.nom as nom_administration, f.nom as nom_forme, m.nom as nom_medicament from Forme_admin_medoc fam join forme f on f.id=fam.id_forme join administration a on a.id = fam.id_administration join medicament m on fam.id_medicament = m.id where fam.id = ? ";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
               this.idMedicament = rs.getInt(2);
               this.idFormeAdmin = rs.getInt(3);
               this.nomAdministration = rs.getString(4);
               this.nomForme = rs.getString(5);
               this.nomMedicament = rs.getString(6);
            }
            preparedStatement.close();
            rs.close();
            
        } catch (Exception e) {
            // c.closeBD();
            throw e;
        }
    }
    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO Forme_admin_medoc VALUES (default , ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, this.getIdMedicament());
            // preparedStatement.setInt(2, this.getIdForme());
            // preparedStatement.setInt(3, this.getIdAdministration());
            preparedStatement.setInt(2, this.getIdFormeAdmin());
            preparedStatement.executeUpdate();
             
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1); 
            }
            this.getById(c);

            generatedKeys.close();
            preparedStatement.close();

            c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    public String buildNomProduit(){
        String nom = this.nomMedicament+" "+this.nomForme+" "+this.nomAdministration;
        return nom;
    }
}
