package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class ComissionEmploye {
    private int id;
    private Date dateVente;
    private int idClient;
    private int idEmploye;
    private double comission_employe;
    private String nomEmploye;
    private double totalVente;

    private int idGenre;
    private String nomGenre;
    private double comissionHomme;
    private double comissionFemme;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDateVente() {
        return dateVente;
    }
    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public int getIdEmploye() {
        return idEmploye;
    }
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public double getComission_employe() {
        return comission_employe;
    }
    public void setComission_employe(double comission_employe) {
        this.comission_employe = comission_employe;
    }
    public String getNomEmploye() {
        return nomEmploye;
    }
    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }
    public double getTotalVente() {
        return totalVente;
    }
    public void setTotalVente(double totalVente) {
        this.totalVente = totalVente;
    }

    public static List<ComissionEmploye> getCommission(Connect conn, Integer idEmploye, Date dateDebut, Date dateFin) throws SQLException {
        System.out.println(idEmploye+" emppppp-");
        // Construction de la requête SQL de base
        List<ComissionEmploye> list = new ArrayList<ComissionEmploye>();
        StringBuilder sql = new StringBuilder(
            "SELECT id, date_vente, id_client, comission_employe, id_employe, nom, total_vente FROM V_Commission_employe WHERE 1=1"
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
        System.out.println(sql.toString());

        // Ajouter la clause GROUP BY si id_employe n'est pas spécifié
        
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
                ComissionEmploye vente = new ComissionEmploye();
                vente.setId(rs.getInt("id"));
                vente.setDateVente(rs.getDate("date_vente"));
                vente.setIdClient(rs.getInt("id_client"));
                vente.setIdEmploye(rs.getInt("id_employe"));
                vente.nomEmploye = rs.getString("nom");
                vente.setComission_employe(rs.getDouble("comission_employe"));
                vente.setTotalVente(rs.getDouble("total_vente"));
                list.add(vente);
            }
        }
        return list;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }public int getIdGenre() {
        return idGenre;
    }public String getNomGenre() {
        return nomGenre;
    }public void setNomGenre(String nomGenre) {
        this.nomGenre = nomGenre;
    }

    public double getComissionFemme() {
        return comissionFemme;
    }public double getComissionHomme() {
        return comissionHomme;
    }

    
    
    public static ComissionEmploye getByGenre(Connect conn, Date dateDebut, Date dateFin) throws SQLException {
        // Construction de la requête SQL de base
        List<ComissionEmploye> list = new ArrayList<ComissionEmploye>();
        ComissionEmploye employee = new ComissionEmploye();
        StringBuilder sql = new StringBuilder(
            "SELECT SUM(comission_employe) as total_comission, Genre.id, Genre.nom  FROM Employe LEFT JOIN Vente ON vente.id_employe = Employe.id JOIN Genre ON employe.id_genre = Genre.id WHERE 1=1"
        );
        if (dateDebut!=null) {
            sql.append(" AND date_vente >= ?");
        }

        // Ajouter une condition pour date_fin, si fourni
        if (dateFin!=null) {
            sql.append(" AND date_vente <= ?");
        }
        sql.append(" GROUP BY Genre.id, GEnre.nom");

        int paramIndex = 1;
        System.out.println(sql.toString());

        try (PreparedStatement stmt = conn.getConnex().prepareStatement(sql.toString())) {
            
            if (dateDebut!=null) {
                stmt.setDate(paramIndex++, dateDebut);
            }
            if (dateFin!=null) {
                stmt.setDate(paramIndex++, dateFin);
            }

            // Exécuter la requête et obtenir le résultat
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                ComissionEmploye vente = new ComissionEmploye();
                
                vente.setComission_employe(rs.getDouble("total_comission"));
                vente.setIdGenre(rs.getInt("id"));
                vente.setNomGenre(rs.getString("nom"));
                System.out.println(rs.getString("nom"));
                if(rs.getString("nom").equals("Homme")){
                    employee.comissionHomme = (rs.getDouble("total_comission"));
                }else {
                    employee.comissionFemme = (rs.getDouble("total_comission"));
                }
                list.add(vente);
            }
            return employee;
        }
    } 
}
