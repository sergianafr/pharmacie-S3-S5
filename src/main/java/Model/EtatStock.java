package Model;

import java.sql.Date;

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

    
}