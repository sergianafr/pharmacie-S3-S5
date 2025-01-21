package Model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import DbUtils.Connect;

public class Vente {
    private int id;
    private Date dateVente;
    private int idClient;
    private int idEmploye;
    private double comission_employe;
    private String nomEmploye;


    public double getComission_employe() {
        return comission_employe;
    }
    public void setComission_employe(double comission_employe) {
        this.comission_employe = comission_employe;
    }public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }public String getNomEmploye() {
        return nomEmploye;
    }
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

    public static List<Vente> getCommission(Connect conn, Integer idEmploye, Date dateDebut, Date dateFin) throws SQLException {
        // Construction de la requête SQL de base
        List<Vente> list = new ArrayList<Vente>();
        StringBuilder sql = new StringBuilder(
            "SELECT id, date_vente, id_client, SUM(comission_employe) as comission_employe, id_employe, nom FROM V_Commission_employe WHERE 1=1"
        );

        // Liste des paramètres de la requête
        int paramIndex = 1;

        // Ajouter une condition pour id_employe, si fourni
        if (idEmploye!= null) {
            sql.append(" AND id_employe = ?");
        }

        // Ajouter une condition pour date_debut, si fourni
        if (dateDebut!=null) {
            sql.append(" AND date_vente >= ?");
        }

        // Ajouter une condition pour date_fin, si fourni
        if (dateFin!=null) {
            sql.append(" AND date_vente <= ?");
        }

        // Ajouter la clause GROUP BY si id_employe n'est pas spécifié
        if (idEmploye==null) {
            sql.append(" GROUP BY id_employe");
        }

        // Préparer la requête
        try (PreparedStatement stmt = conn.getConnex().prepareStatement(sql.toString())) {
            // Ajouter les paramètres à la requête
            if (idEmploye!=null) {
                stmt.setInt(paramIndex++, idEmploye);
            }
            if (dateDebut!=null) {
                stmt.setDate(paramIndex++, dateDebut);
            }
            if (dateFin!=null) {
                stmt.setDate(paramIndex++, dateFin);
            }

            // Exécuter la requête et obtenir le résultat
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Vente vente = new Vente();
                vente.setId(rs.getInt("id"));
                vente.setDateVente(rs.getDate("date_vente"));
                vente.setIdClient(rs.getInt("id_client"));
                vente.setIdEmploye(rs.getInt("id_employe"));
                vente.nomEmploye = rs.getString("nom");
                vente.setComission_employe(rs.getDouble("comission_employe"));
                
                list.add(vente);
            }
        }
        return list;
    }


    
    
}
