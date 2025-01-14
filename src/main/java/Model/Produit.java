package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Produit {
    public int id;
    public String nom;
    public boolean surOrdonnance;
    public int idCategorie;
    private double montant;
    private String nomCategorie;
    
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

            // c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    public void setSurOrdonnance(boolean surOrdonnance) {
        this.surOrdonnance = surOrdonnance;
    }
    public double getMontant() {
        return montant;
    }public String getNomCategorie() {
        return nomCategorie;
    }

    public static  List<Produit> getAll(Connect c) throws Exception{
        try {
            String sql = """
            SELECT * FROM v_details_produit
            """;
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Produit> results = new ArrayList<Produit>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Boolean surOrdonnance = rs.getBoolean(3);
                int idCategorie = rs.getInt(4);
                String nomCategorie =  rs.getString(5);
                double montant = rs.getDouble(6);
                Produit p = new Produit(id, nom, surOrdonnance, idCategorie);
                p.setNomCategorie(nomCategorie);
                p.setSurOrdonnance(surOrdonnance);
                p.setMontant(montant);
                results.add(p);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // // c.closeBD();
            throw e;
        }
    }

   
}
