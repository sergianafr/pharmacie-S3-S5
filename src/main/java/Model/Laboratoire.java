package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Laboratoire {
    private int id;
    private String nom;
    private int id_pays_origine;

    public int getId() {
        return id;
    }public int getId_pays_origine() {
        return id_pays_origine;
    }public String getNom() {
        return nom;
    }
    public void setId(int id) {
        this.id = id;
    }public void setId_pays_origine(int id_pays_origine) {
        this.id_pays_origine = id_pays_origine;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public Laboratoire(){    }
    public Laboratoire(int id, String nom, int id_pays_origine) {
        this.id = id;
        this.nom = nom;
        this.id_pays_origine = id_pays_origine;
    }

    public static  List<Laboratoire> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Laboratoire";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Laboratoire> results = new ArrayList<Laboratoire>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                int idPaysOrigine = rs.getInt(3);
                Laboratoire objet = new Laboratoire(id, nom, idPaysOrigine);
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
