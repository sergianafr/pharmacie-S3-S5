package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Pays {
    private int id;
    private String nom;

    public Pays() {}

    public Pays(int id, String nom) {
        this.id = id;
        this.nom = nom;
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

    public static List<Pays> getAll(Connect c) throws Exception {
        List<Pays> results = new ArrayList<>();
        String sql = "SELECT * FROM pays";

        try (PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                Pays pays = new Pays(id, nom);
                results.add(pays);
            }

        } catch (Exception e) {
            throw e;
        }
        return results;
    }
}
