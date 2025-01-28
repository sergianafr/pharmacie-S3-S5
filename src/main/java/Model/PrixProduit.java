package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class PrixProduit {
    private int id;
    private Date dateInsertion;
    private double montant;
    private int idProduit;
    private String nom;

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

    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }

    public static  List<PrixProduit> getAll(int idProduit, Date dateDebut, Date dateFin, Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM v_detail_prix where 1=1 ";
            if(idProduit!=0){
                sql += " AND id_Produit=?";
            }
            if (dateDebut!=null) {
                sql+=" AND date_insertion >= ?";
            }
    
            int paramIndex = 1;
            // Ajouter une condition pour date_fin, si fourni
            if (dateFin!=null) {
                sql+=" AND date_insertion <= ?";
            }
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            System.out.println(sql);
            
            if (idProduit != 0) {
                preparedStatement.setInt(1, idProduit);
            }
            if (dateDebut!=null) {
                preparedStatement.setDate(paramIndex++, dateDebut);
            }
            System.out.println("paraaam "+paramIndex);
            if (dateFin!=null) {
                preparedStatement.setDate(paramIndex++, dateFin);
            }
            ResultSet rs = preparedStatement.executeQuery();
            
            List<PrixProduit> results = new ArrayList<PrixProduit>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                Date dateInsertion = rs.getDate(2);
                double montant = rs.getDouble(3);
                int id_produit = rs.getInt(4);
                String nom = rs.getString(5);
                PrixProduit objet = new PrixProduit(id, dateInsertion, montant, id_produit);
                objet.setNom(nom);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            throw e;
        }
    }
    
}

