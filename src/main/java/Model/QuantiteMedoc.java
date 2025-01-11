package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class QuantiteMedoc {
    public int id;
    public double qte;
    public int idFormeAdminMedoc;
    public int idUnite;
    public int idProduit;

    private FormeAdminMedoc formeAdminMedoc;
    private String nomUnite;

    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    public int getIdUnite() {
        return idUnite;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setIdFormeAdminMedoc(int idFormeAdminMedoc) {
        this.idFormeAdminMedoc = idFormeAdminMedoc;
    }public void setQte(double qte) {
        this.qte = qte;
    }
    public int getId() {
        return id;
    }public int getIdFormeAdminMedoc() {
        return idFormeAdminMedoc;
    }public double getQte() {
        return qte;
    }
    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO quantite_medoc VALUES (default , ?, ?, ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, this.getQte());
            preparedStatement.setInt(2, this.getIdUnite());
            preparedStatement.setInt(3, this.getIdFormeAdminMedoc());
            preparedStatement.setInt(4, this.getIdProduit());


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

    /* TO DO: insert with produit et produit name builder */
    public String buildNomProduit(){
        return " - "+String.valueOf(qte)+nomUnite;
    }
}
