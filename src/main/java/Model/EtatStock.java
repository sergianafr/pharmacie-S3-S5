package Model;

import java.sql.Date;

public class EtatStock {
    private int idProduit;
    private Date datePeremption;
    private double qteDispo;

    // Constructeur
    public EtatStock(int idProduit, Date datePeremption, double qteDispo) {
        this.idProduit = idProduit;
        this.datePeremption = datePeremption;
        this.qteDispo = qteDispo;
    }

    // Getters et Setters
    public int getIdProduit() {
        return idProduit;
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