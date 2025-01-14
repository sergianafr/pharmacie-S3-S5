package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MvtStock {
    private int id;
    private Date dateInsertion;
    private Date dateFabrication;
    private Date datePeremption;
    private double qteEntree;
    private double qteSortie;
    private int idProduit;

    // Constructeur
    public MvtStock() {
    }

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

    public void insert(Connection con) throws SQLException {
        String query = "INSERT INTO mvt_stock (date_fabrication, date_peremption, qte_entree, id_produit) " +
                    "VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(query)) {
            pstmt.setDate(1, dateFabrication);
            pstmt.setDate(2, datePeremption);
            pstmt.setDouble(3, qteEntree);
            pstmt.setInt(4, idProduit);
            pstmt.executeUpdate();
        }
    }

    public List<MvtStock> getAll(Connection con) throws SQLException {
        List<MvtStock> mouvements = new ArrayList<>();
        String query = "SELECT * FROM mvt_stock";
        try (PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                MvtStock mvt = new MvtStock();
                mvt.setId(rs.getInt("id"));
                mvt.setDateInsertion(rs.getDate("date_insertion"));
                mvt.setDateFabrication(rs.getDate("date_fabrication"));
                mvt.setDatePeremption(rs.getDate("date_peremption"));
                mvt.setQteEntree(rs.getDouble("qte_entree"));
                mvt.setQteSortie(rs.getDouble("qte_sortie"));
                mvt.setIdProduit(rs.getInt("id_produit"));
                mouvements.add(mvt);
            }
        }
        return mouvements;
    }


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