package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Vente {
    private int id;
    private Date dateVente;
    private List<VenteDetail> venteDetails = new ArrayList<VenteDetail>(); 
    // Constructeur
    public Vente(int id, Date dateVente) {
        this.id = id;
        this.dateVente = dateVente;
    }

    public Date getDateVente() {
        return dateVente;
    }public int getId() {
        return id;
    }public List<VenteDetail> getVenteDetails() {
        return venteDetails;
    }
    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }public void setId(int id) {
        this.id = id;
    }public void setVenteDetails(List<VenteDetail> venteDetails) {
        this.venteDetails = venteDetails;
    }
    // Getters et Setters

    @Override
    public String toString() {
        return "Vente{" +
                "id=" + id +
                ", dateVente=" + dateVente +
                '}';
    }

    public void create(Connect c) throws Exception {
        try {
            String Query = "INSERT INTO vente (date_vente) VALUES (CURRENT_DATE)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1); 
            }
            
            c.getConnex().commit();
            generatedKeys.close();
            preparedStatement.close();
        } catch (Exception e) {
            throw e;
        }
    }

    public void insertWDetails(Connect c)throws Exception{
        try {
            this.create(c);
            for (VenteDetail venteDetail : venteDetails) {
                venteDetail.setIdVente(id);
                venteDetail.create(c);
            }
        } catch (Exception e) {
           throw e;
        }
    }
    
}
