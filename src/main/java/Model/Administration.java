package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Administration  {
    public int id;
    public String nom;
    // public String nomTable = "Administration";
    
    public Administration(int id, String nom){
        // super(id, nom);
        this.id = id;
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }public String getNom() {
        return nom;
    }
 
    public Administration(){}

    public static List<Administration> getByIdForme(Connect c, int idForme)throws Exception{
        try {
            String sql = "SELECT * FROM Administration WHERE id in (SELECT id_administration from forme_administration where id_forme = ? )";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            preparedStatement.setInt(1, idForme);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Administration> results = new ArrayList<Administration>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Administration objet = new Administration(id, nom);
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
    public static List<Administration> getAll(Connect c)throws Exception{
        try {
            String sql = "SELECT * FROM Administration";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Administration> results = new ArrayList<Administration>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Administration objet = new Administration(id, nom);
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
