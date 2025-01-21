package Model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Employe {
    private int id;
    private String nom;
    private Date dateNaissance;
    private int idGenre;
    private int idPoste;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public int getIdGenre() {
        return idGenre;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    public int getIdPoste() {
        return idPoste;
    }
    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    
    public Employe(int id, String nom, Date dateNaissance, int idGenre, int idPoste) {
        this.id = id;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.idGenre = idGenre;
        this.idPoste = idPoste;
    }

    public  static List<Employe> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM Employe";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Employe> results = new ArrayList<Employe>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                Date date = rs.getDate(3);
                int idgenre = rs.getInt(4);
                int idposte = rs.getInt(5);
                Employe objet = new Employe(id, nom, date, idgenre, idposte );
                results.add(objet);   
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            // c.closeBD();
            throw e;
        }
    }

    
}
