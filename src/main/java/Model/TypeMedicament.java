package Model;

public class TypeMedicament extends TypeObjet {
    public String nomTable = "type_medicament";
    
    public TypeMedicament(int id, String nom){
        super(id, nom);
    }
    public TypeMedicament(){
        super();
        this.setNomTable("type_medicament");
    }
}
