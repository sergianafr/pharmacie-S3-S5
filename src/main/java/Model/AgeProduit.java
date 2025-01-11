package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class AgeProduit {
    private int id;
    private int idProduit;
    private int idAge;

    public void setId(int id) {
        this.id = id;
    }public void setIdAge(int idAge) {
        this.idAge = idAge;
    }public void setidProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public int getId() {
        return id;
    }public int getIdAge() {
        return idAge;
    }public int getidProduit() {
        return idProduit;
    }

    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO age_produit VALUES (default , ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, this.getidProduit());
            preparedStatement.setInt(2, this.getIdAge());


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
    public AgeProduit(int id, int idProduit, int idAge) {
        this.id = id;
        this.idProduit = idProduit;
        this.idAge = idAge;
    }
    public AgeProduit(){}
}

