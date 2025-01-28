package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import DbUtils.Connect;

public class PrixProduit {
    private int id;
    private Date dateInsertion;
    private double montant;
    private int idProduit;

    // Constructeur
    public PrixProduit(int id, Date dateInsertion, double montant, int idProduit) {
        this.id = id;
        this.dateInsertion = dateInsertion;
        this.montant = montant;
        this.idProduit = idProduit;
    }


    public PrixProduit(double montant, int idProduit) {
        this.montant = montant;
        this.idProduit = idProduit;
    }

    public Date getDateInsertion() {
        return dateInsertion;
    }public int getId() {
        return id;
    }public int getIdProduit() {
        return idProduit;
    }public double getMontant() {
        return montant;
    }
    public void setDateInsertion(Date dateInsertion) {
        this.dateInsertion = dateInsertion;
    }public void setId(int id) {
        this.id = id;
    }public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }public void setMontant(double montant) {
        this.montant = montant;
    }
    // Getters et Setters

    @Override
    public String toString() {
        return "PrixProduit{" +
                "id=" + id +
                ", dateInsertion=" + dateInsertion +
                ", montant=" + montant +
                ", idProduit=" + idProduit +
                '}';
    }

    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO prix_produit VALUES (default , CURRENT_DATE, ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDouble(1, this.getMontant());
            preparedStatement.setInt(2, this.getIdProduit());


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
}

