package Model;

import java.sql.Date;

public class MvtStock {
    private int id;
    private Date dateInsertion;
    private Date dateFabrication;
    private Date datePeremption;
    private double qteEntree;
    private double qteSortie;
    private int idProduit;

    // Constructeur
    public MvtStock(int id, Date dateInsertion, Date dateFabrication, Date datePeremption, 
                    double qteEntree, double qteSortie, int idProduit) {
        this.id = id;
        this.dateInsertion = dateInsertion;
        this.dateFabrication = dateFabrication;
        this.datePeremption = datePeremption;
        this.qteEntree = qteEntree;
        this.qteSortie = qteSortie;
        this.idProduit = idProduit;
    }
    public Date getDateFabrication() {
        return dateFabrication;
    }public Date getDateInsertion() {
        return dateInsertion;
    }public Date getDatePeremption() {
        return datePeremption;
    }public int getId() {
        return id;
    }public int getIdProduit() {
        return idProduit;
    }public double getQteEntree() {
        return qteEntree;
    }
    public double getQteSortie() {
        return qteSortie;
    }
    public void setDateFabrication(Date dateFabrication) {
        this.dateFabrication = dateFabrication;
    }public void setDateInsertion(Date dateInsertion) {
        this.dateInsertion = dateInsertion;
    }public void setDatePeremption(Date datePeremption) {
        this.datePeremption = datePeremption;
    }public void setId(int id) {
        this.id = id;
    }public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }public void setQteEntree(double qteEntree) {
        this.qteEntree = qteEntree;
    }public void setQteSortie(double qteSortie) {
        this.qteSortie = qteSortie;
    }

    // Getters et Setters
    // (générés automatiquement ou écrits ici)

    @Override
    public String toString() {
        return "MvtStock{" +
                "id=" + id +
                ", dateInsertion=" + dateInsertion +
                ", dateFabrication=" + dateFabrication +
                ", datePeremption=" + datePeremption +
                ", qteEntree=" + qteEntree +
                ", qteSortie=" + qteSortie +
                ", idProduit=" + idProduit +
                '}';
    }
}