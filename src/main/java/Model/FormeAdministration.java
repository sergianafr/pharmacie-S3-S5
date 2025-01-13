package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class FormeAdministration {
    public int id;
    public int idAdministration;
    public int idForme;
    private String nomAdministration;
    private String nomForme;

    public void setId(int id) {
        this.id = id;
    }public void setIdAdministration(int idAdministration) {
        this.idAdministration = idAdministration;
    }public void setIdForme(int idForme) {
        this.idForme = idForme;
    }
    public int getId() {
        return id;
    }public int getIdAdministration() {
        return idAdministration;
    }public int getIdForme() {
        return idForme;
    }

    public FormeAdministration(){}
    public FormeAdministration(int id, int idAdministration, int idForme) {
        this.id = id;
        this.idAdministration = idAdministration;
        this.idForme = idForme;
    }
    public FormeAdministration(int id, int idAdministration, int idForme, String nomAdministration, String nomForme) {
        this.id = id;
        this.idAdministration = idAdministration;
        this.idForme = idForme;
        this.nomAdministration = nomAdministration;
        this.nomForme = nomForme;
    }
    public static List<FormeAdministration> getByIdForme(Connect c, int idForme)throws Exception{
        try {
            String sql = "SELECT forme_administration.*, forme.nom as nom_forme, administration.nom as nom_administration from forme_administration join forme on forme.id=forme_administration.id_forme join administration on administration.id=forme_administration.id_administration where id_forme = ? ";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            preparedStatement.setInt(1, idForme);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<FormeAdministration> results = new ArrayList<FormeAdministration>();
            while(rs.next()){
                // System.out.println(rs);
                int id = rs.getInt(1);
                int idAdministration = rs.getInt(2);
                String nomForme = rs.getString(4);
                String nomAdministration = rs.getString(5);

                FormeAdministration objet = new FormeAdministration(id, idAdministration, idForme, nomAdministration, nomForme);
                results.add(objet);   
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
