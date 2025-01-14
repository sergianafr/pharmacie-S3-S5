/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DbUtils.Connect;

import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SERGIANA
 */
public class TypeObjet {
    public int id;
    public String nom;
    public String nomTable;

    public TypeObjet() {}

    public String getNomTable() {
        return nomTable;
    }

    public void setNomTable(String nomTable) {
        this.nomTable = nomTable;
    }

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
    public TypeObjet(int id, String nom){
        this.id = id;
        this.nom = nom;
    }
    
    public void create(Connect c)throws Exception{
        try {      
            String Query = "INSERT INTO ? VALUES (default , ?)";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query);
            preparedStatement.setString(1, this.getNomTable());
            preparedStatement.setString(2 , this.getNom());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.getConnex().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    public void delete(Connect c)throws Exception{
        try {      
            String Query = "DELETE FROM ? WHERE id = ?";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query);
            preparedStatement.setString(1, this.getNomTable());
            preparedStatement.setInt(2 , this.getId());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.getConnex().commit();
        } catch (Exception e) {
            c.getConnex().rollback();
            throw e;
        }
    }
    
    public  List<TypeObjet> getAll(Connect c) throws Exception{
        try {
            String sql = "SELECT * FROM "+this.nomTable;
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(sql);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            List<TypeObjet> results = new ArrayList<TypeObjet>();
            while(rs.next()){
                System.out.println(rs);
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                TypeObjet objet = new TypeObjet(id, nom);
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
