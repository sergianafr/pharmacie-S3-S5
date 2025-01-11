package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Forme {
    public int id;
    public String nom;
    public int idUnite;   

    public Forme(int id, String nom, int idUnite) {
        this.id = id;
        this.nom = nom;
        this.idUnite = idUnite;
    }
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

    public int getIdUnite() {
        return idUnite;
    }
    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    public Forme(){}

    public static  List<Forme> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Forme";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Forme> results = new ArrayList<Forme>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                int idUnite = rs.getInt(3);
                Forme objet = new Forme(id, nom, idUnite);
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
}
