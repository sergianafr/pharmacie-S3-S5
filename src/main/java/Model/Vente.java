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
    private int idClient;
    private int idEmploye;

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }public int getIdClient() {
        return idClient;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public int getIdEmploye() {
        return idEmploye;
    }
    private List<VenteDetail> venteDetails = new ArrayList<VenteDetail>(); 
    // Constructeur
    public Vente(int id, Date dateVente, int idEmploye) {
        this.id = id;
        this.dateVente = dateVente;
        this.idEmploye = idEmploye;
    }

    public Vente() {}
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
            String Query = "INSERT INTO vente (date_vente, id_client, id_employe) VALUES (CURRENT_DATE, ?, ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,this.getIdClient());
            preparedStatement.setInt(2, idEmploye);
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
