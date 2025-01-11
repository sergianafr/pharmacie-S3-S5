package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class AgeMedicament {
    private int id;
    private int idMedicament;
    private int idAge;

    public void setId(int id) {
        this.id = id;
    }public void setIdAge(int idAge) {
        this.idAge = idAge;
    }public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }
    public int getId() {
        return id;
    }public int getIdAge() {
        return idAge;
    }public int getIdMedicament() {
        return idMedicament;
    }

    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO age_medicament VALUES (default , ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, this.getIdMedicament());
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
    public AgeMedicament(int id, int idMedicament, int idAge) {
        this.id = id;
        this.idMedicament = idMedicament;
        this.idAge = idAge;
    }
    public AgeMedicament(){}
}
