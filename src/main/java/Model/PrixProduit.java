package Model;

import java.sql.Date;

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
}

