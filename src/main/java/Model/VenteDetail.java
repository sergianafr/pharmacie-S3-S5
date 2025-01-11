package Model;

import java.sql.PreparedStatement;

import DbUtils.Connect;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class VenteDetail {
    private int id;
    private double qte;
    private double pu;
    private double prixTotal;
    private int idVente;
    private int idProduit;
    private String nomProduit;
    private Date dateVente;
    // Constructeur
    public VenteDetail(int id, double qte, double pu, double prixTotal, int idVente, int idProduit) {
        this.id = id;
        this.qte = qte;
        this.pu = pu;
        this.prixTotal = prixTotal;
        this.idVente = idVente;
        this.idProduit = idProduit;
    }

    // Getters et Setters

    
    @Override
    public String toString() {
        return "VenteDetail{" +
                "id=" + id +
                ", qte=" + qte +
                ", pu=" + pu +
                ", prixTotal=" + prixTotal +
                ", idVente=" + idVente +
                ", idProduit=" + idProduit +
                '}';
    }

    public void create(Connect c) throws Exception {
        try {
            String Query = "INSERT INTO vente_detail (qte, id_vente, id_produit) VALUES (?, ?, ?)";
            
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query);
    
            preparedStatement.setDouble(1, this.qte); 
            preparedStatement.setInt(2, this.idVente); 
            preparedStatement.setInt(3, this.idProduit); 
    
            preparedStatement.executeUpdate();
    
            preparedStatement.close();
            c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQte() {
        return qte;
    }

   
    public void setQte(double qte) {
        this.qte = qte;
    }
    

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public int getIdVente() {
        return idVente;
    }

    public void setIdVente(int idVente) {
        this.idVente = idVente;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    
    public static  List<VenteDetail> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT vente_detail.*, produit.nom as nom_produit, vente.date_vente FROM Vente_Detail join produit on produit.id = vente_detail.id_produit join vente on vente.id = vente_detail.id_vente";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<VenteDetail> results = new ArrayList<VenteDetail>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                double qte = rs.getDouble(2);
                double pu = rs.getDouble(3);
                double prixTotal = rs.getDouble(4);
                int idVente = rs.getInt(5);
                int idProduit = rs.getInt(6);
                String nom = rs.getString(7);
                VenteDetail objet = new VenteDetail(id, qte, pu, prixTotal, idVente, idProduit);
                objet.nomProduit = nom;
                objet.dateVente = rs.getDate(8);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            c.closeBD();
            throw e;
        }
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }
    

    public static List<VenteDetail> filtrer (Connect c, int id_forme, int id_age)throws Exception{
        try {
            String sql = "SELECT * FROM filter_ventedetail_by_forme_age(?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<VenteDetail> results = new ArrayList<VenteDetail>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                double qte = rs.getDouble(2);
                double pu = rs.getDouble(3);
                double prixTotal = rs.getDouble(4);
                int idVente = rs.getInt(5);
                int idProduit = rs.getInt(6);
                String nom = rs.getString(7);
                VenteDetail objet = new VenteDetail(id, qte, pu, prixTotal, idVente, idProduit);
                objet.nomProduit = nom;
                objet.dateVente = rs.getDate(8);
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            c.closeBD();
            throw e;
        }
    }
}