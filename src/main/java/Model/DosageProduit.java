package Model;

public class DosageProduit {
    private int id;
    private double dosage;
    private int idUnite;
    private int idProduit;
    
    public DosageProduit(int id, double dosage, int idUnite, int idProduit) {
        this.id = id;
        this.dosage = dosage;
        this.idUnite = idUnite;
        this.idProduit = idProduit;
    }
    
    public DosageProduit() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getDosage() {
        return dosage;
    }
    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
    public int getIdUnite() {
        return idUnite;
    }
    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    
}
