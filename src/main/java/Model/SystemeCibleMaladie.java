package Model;

public class SystemeCibleMaladie {
    private int id;
    private int idSystemeCible;
    private int idMaladie;

    public void setId(int id) {
        this.id = id;
    }public void setIdMaladie(int idMaladie) {
        this.idMaladie = idMaladie;
    }public void setidSystemeCible(int idSystemeCible) {
        this.idSystemeCible = idSystemeCible;
    }
    public int getId() {
        return id;
    }public int getIdMaladie() {
        return idMaladie;
    }public int getidSystemeCible() {
        return idSystemeCible;
    }
}
