package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Client {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }public String getNom() {
        return nom;
    }
    public void setId(int id) {
        this.id = id;
    }public void setNom(String nom) {
        this.nom = nom;
    }

    public Client(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public  static List<Client> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Client";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Client> results = new ArrayList<Client>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Client objet = new Client(id, nom);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // c.closeBD();
            throw e;
        }
    }
}
