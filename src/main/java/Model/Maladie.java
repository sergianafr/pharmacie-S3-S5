package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DbUtils.Connect;

public class Maladie {
    private int id;
    private String nom;
    private boolean estContagieuse;
    private boolean estGrave;
    private boolean estRare;

    public Maladie() {}

    public Maladie(int id, String nom, boolean estContagieuse, boolean estGrave, boolean estRare) {
        this.id = id;
        this.nom = nom;
        this.estContagieuse = estContagieuse;
        this.estGrave = estGrave;
        this.estRare = estRare;
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setEstContagieuse(boolean estContagieuse) {
        this.estContagieuse = estContagieuse;
    }public void setEstGrave(boolean estGrave) {
        this.estGrave = estGrave;
    }public void setEstRare(boolean estRare) {
        this.estRare = estRare;
    }public void setId(int id) {
        this.id = id;
    }public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean estContagieuse() {
        return estContagieuse;
    }
    public boolean estGrave() {
        return estGrave;
    }
    public boolean estRare() {
        return estRare;
    }

    public  static List<Maladie> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Maladie";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Maladie> results = new ArrayList<Maladie>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Boolean estContagieuse = rs.getBoolean(3);
                Boolean estGrave = rs.getBoolean(4);
                Boolean estRare = rs.getBoolean(5);
                Maladie objet = new Maladie(id, nom, estContagieuse, estGrave, estRare);
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

    public static List<Maladie> getByIdMedicament(Connect c, int idMedicament) throws Exception{
        try {
            String query = 
            "SELECT m.* " +
            "FROM Maladie_medicament mm " +
            "INNER JOIN Maladie m ON mm.id_maladie = m.id " +
            "WHERE mm.id_medicament = ?";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(query);
            preparedStatement.setInt(1, idMedicament);
            ResultSet rs = preparedStatement.executeQuery();
             
            List<Maladie> results = new ArrayList<Maladie>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Boolean estContagieuse = rs.getBoolean(3);
                Boolean estGrave = rs.getBoolean(4);
                Boolean estRare = rs.getBoolean(5);
                Maladie objet = new Maladie(id, nom, estContagieuse, estGrave, estRare);
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

    public static HashMap<Integer, List<Maladie>> getByListMedicament(Connect c, List<Medicament> list) throws Exception {
        HashMap<Integer, List<Maladie>> results = new HashMap<Integer, List<Maladie>>();
        try {
            for(Medicament m:list) {
                results.put(m.getId(), Maladie.getByIdMedicament(c, m.getId())); 
            }
        } catch (Exception e) {
            throw e;
        }
        return results;
    }

    

}
