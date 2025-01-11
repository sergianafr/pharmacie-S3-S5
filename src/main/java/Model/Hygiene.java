package Model;

public class Hygiene {
    public int id;
    public String nom;
    public int idProduit;

    public void setId(int id) {
        this.id = id;
    }public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getId() {
        return id;
    }public int getIdProduit() {
        return idProduit;
    }public String getNom() {
        return nom;
    }
}
