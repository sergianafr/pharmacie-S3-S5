package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DbUtils.Connect;

public class Age {
    public int id;
    public String nom;
    public String nomTable = "Age";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Age(int id, String nom){
        this.id =id;
        this.nom =nom;
    }

    public static  List<Age> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Age";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Age> results = new ArrayList<Age>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Age objet = new Age(id, nom);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            c.closeBD();
            throw e;
        }
    }
    public Age(){}

    public static List<Age> getByIdMedicament(Connect c, int idMedicament) throws Exception{
        try {
            String query = 
                "SELECT a.*" +
                "FROM age_medicament am " +
                "INNER JOIN Age a ON am.id_age = a.id " +
                "WHERE am.id_medicament = ?";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(query);
            preparedStatement.setInt(1, idMedicament);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Age> results = new ArrayList<Age>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Age objet = new Age(id, nom);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            c.closeBD();
            throw e;
        }
    }

     public static HashMap<Integer, List<Age>> getByListMedicament(Connect c, List<Medicament> list) throws Exception {
        HashMap<Integer, List<Age>> results = new HashMap<Integer, List<Age>>();
        try {
            for(Medicament m:list) {
                results.put(m.getId(), Age.getByIdMedicament(c, m.getId())); 
            }
        } catch (Exception e) {
            throw e;
        }
        return results;
    }
}
