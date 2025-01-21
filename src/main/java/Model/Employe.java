package Model;

import java.sql.Date;

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

    
}
