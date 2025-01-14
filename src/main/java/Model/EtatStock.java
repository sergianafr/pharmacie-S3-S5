package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class EtatStock {
    private int idProduit;
    private String nom;
    private Date datePeremption;
    private double qteEntree;
    private double qteSortie;
    private double qteDispo;

    // Constructeur
    public EtatStock(int idProduit, String nom, Date datePeremption, double qteEntree, double qteSortie, double qteDispo) {
        this.idProduit = idProduit;
        this.datePeremption = datePeremption;
        this.qteDispo = qteDispo;
        this.qteEntree = qteEntree;
        this.nom = nom;
        this.qteSortie = qteSortie;
    }

    // Getters et Setters
    public int getIdProduit() {
        return idProduit;
    }
    public String getNom() {
        return nom;
    }
    
    public double getQteEntree() {
        return qteEntree;
    }public double getQteSortie() {
        return qteSortie;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }public void setQteEntree(double qteEntree) {
        this.qteEntree = qteEntree;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Date getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }

    public double getQteDispo() {
        return qteDispo;
    }

    public void setQteDispo(double qteDispo) {
        this.qteDispo = qteDispo;
    }

    @Override
    public String toString() {
        return "EtatStock{" +
                "idProduit=" + idProduit +
                ", datePeremption=" + datePeremption +
                ", qteDispo=" + qteDispo +
                '}';
    }
    public static  List<EtatStock> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM V_Etat_Stock";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<EtatStock> results = new ArrayList<EtatStock>();
            while(rs.next()){
                System.out.println(rs);
                int idProduit = rs.getInt(1);
                String nom = rs.getString(2);
                Date datePeremption = rs.getDate(3);
                double qteEntree = rs.getDouble(4);
                double qteSortie = rs.getDouble(5);
                double qteDispo = rs.getDouble(6);
                EtatStock objet = new EtatStock(idProduit, nom, datePeremption, qteEntree, qteSortie, qteDispo);
                
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