package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Produit {
    public int id;
    public String nom;
    public boolean surOrdonnance;
    public int idCategorie;
    
    public Produit(int id, String nom, boolean surOrdonnance, int idCategorie) {
        this.id = id;
        this.nom = nom;
        this.surOrdonnance = surOrdonnance;
        this.idCategorie = idCategorie;
    }

    public int getId() {
        return id;
    }
    public int getIdCategorie() {
        return idCategorie;
    }public String getNom() {
        return nom;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean surOrdonnance() {
        return surOrdonnance;
    }
    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO produit VALUES (default , ?, ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, this.getNom());
            preparedStatement.setBoolean(2, this.surOrdonnance());
            preparedStatement.setInt(3, this.getIdCategorie());


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

    public static List<Produit> getAll(Connect c) throws Exception {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT id, nom, sur_ordonnance, id_categorie FROM produit";
        try (Connection conn = c.getConnex();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                boolean surOrdonnance = rs.getBoolean("sur_ordonnance");
                int idCategorie = rs.getInt("id_categorie");

                Produit produit = new Produit(id, nom, surOrdonnance, idCategorie);
                produits.add(produit);
            }
        } catch (Exception e) {
            throw e;
        }
        return produits;
    }
}
