package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class MaladieMedicament {
    public int id;
    public int idMaladie;
    public int idMedicament;

    public int getId() {
        return id;
    }public int getIdMaladie() {
        return idMaladie;
    }public int getIdMedicament() {
        return idMedicament;
    }
    public void setId(int id) {
        this.id = id;
    }public void setIdMaladie(int idMaladie) {
        this.idMaladie = idMaladie;
    }public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }
    public MaladieMedicament(int id, int idMaladie, int idMedicament) {
        this.id = id;
        this.idMaladie = idMaladie;
        this.idMedicament = idMedicament;
    }

    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO maladie_medicament VALUES (default , ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(2, this.getIdMedicament());
            preparedStatement.setInt(1, this.getIdMaladie());

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
    
}
