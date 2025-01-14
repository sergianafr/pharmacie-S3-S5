package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class ConseilMois {
    private int id;
    private int idProduit;
    private Date dateDebut;
    private Date dateFin;
    public String nom;
    public boolean surOrdonnance;
    public int idCategorie;
    private double montant;
    private String nomCategorie;
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public boolean isSurOrdonnance() {
        return surOrdonnance;
    }
    public void setSurOrdonnance(boolean surOrdonnance) {
        this.surOrdonnance = surOrdonnance;
    }
    public int getIdCategorie() {
        return idCategorie;
    }
    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
    public double getMontant() {
        return montant;
    }
    public void setMontant(double montant) {
        this.montant = montant;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }
    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    public boolean surOrdonnance(){return surOrdonnance;}
    public ConseilMois(int id, int idProduit, Date dateDebut, Date dateFin, String nom, boolean surOrdonnance,
            int idCategorie, double montant, String nomCategorie) {
        this.id = id;
        this.idProduit = idProduit;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nom = nom;
        this.surOrdonnance = surOrdonnance;
        this.idCategorie = idCategorie;
        this.montant = montant;
        this.nomCategorie = nomCategorie;
    }
    public Date getDateDebut() {
        return dateDebut;
    }public Date getDateFin() {
        return dateFin;
    }
    public int getId() {
        return id;
    }public int getIdProduit() {
        return idProduit;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }public void setId(int id) {
        this.id = id;
    }public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO conseil_mois(id_produit) VALUES (?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, this.getIdProduit());


            preparedStatement.executeUpdate();
            
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1); 
            }

            generatedKeys.close();
            preparedStatement.close();

            // c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public static  List<ConseilMois> getAllCurrent(Connect c) throws Exception{
        try {
            String sql = """
            SELECT * FROM v_details_conseil WHERE date_fin >= CURRENT_DATE AND date_debut<= CURRENT_DATE
            """;
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<ConseilMois> results = new ArrayList<ConseilMois>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Boolean surOrdonnance = rs.getBoolean(3);
                int idCategorie = rs.getInt(4);
                String nomCategorie =  rs.getString(5);
                double montant = rs.getDouble(6);
                Date dateDebut = rs.getDate(7);
                Date dateFin = rs.getDate(8);
                ConseilMois p = new ConseilMois(0, id, dateDebut, dateFin, nom, surOrdonnance, idCategorie, montant, nomCategorie);
                results.add(p);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // // c.closeBD();
            throw e;
        }
    }
    public static  List<ConseilMois> filtre(Connect c, Date dateMin, Date dateMax) throws Exception{
        try {
            String sql = """
            SELECT * FROM v_details_conseil WHERE 1=1
            """;
            int count = 0;
            if(dateMin != null){
                sql+=" AND date_debut >= ? "; 
                count++;
            } if(dateMax != null){
                sql += " AND date_fin <= ?";
                count++;
            }
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            if(dateMin != null){
                preparedStatement.setDate(count, dateMin);
            } if(dateMax != null){
                preparedStatement.setDate(count, dateMax);
            }
            ResultSet rs = preparedStatement.executeQuery();
            
            List<ConseilMois> results = new ArrayList<ConseilMois>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Boolean surOrdonnance = rs.getBoolean(3);
                int idCategorie = rs.getInt(4);
                String nomCategorie =  rs.getString(5);
                double montant = rs.getDouble(6);
                Date dateDebut = rs.getDate(7);
                Date dateFin = rs.getDate(8);
                ConseilMois p = new ConseilMois(0, id, dateDebut, dateFin, nom, surOrdonnance, idCategorie, montant, nomCategorie);
                results.add(p);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // // c.closeBD();
            throw e;
        }
    }
}
