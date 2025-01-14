/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DbUtils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author SERGIANA
 */
public class Connect {
    String url;
    String username;
    String password;
    Connection connex;
    String type; 
    private static final String DATABASE = "pharmacie";
    private static final String PASSWORD = "Etu002610";

    public Connect (){
    }
    public void closeBD()
    {
        try 
        {
            if(this.connex != null){
                this.connex.close();
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void rollback()
    {
        try
        {
            this.connex.rollback();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void setUsername (String user)
    {
        this.username=user;
    }
    public void setPassword (String pass){
        this.password = pass;
    }
    public void setUrl(String url){
        this.url=url;
    }
    private void setType(String type){
        this.type=type;
    }
    public String getType(){
        return this.type;
    }
    public Connection getConnex (){
        return this.connex;
    }
    
    public void connectToPostgres() throws Exception{
        try{
            this.setUsername(username);
            this.setPassword(password);
            Class.forName("org.postgresql.Driver");
            this.connex = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+DATABASE+"?charSet=UTF-8", "postgres", PASSWORD);
            this.connex.setAutoCommit(false);
            this.setType("postgre");

        }
        catch(Exception e){
            throw e;
        }
    }
    
    public void connect(String url, String username, String password, String database, String className) throws Exception{
        try{
            this.setUsername(username);
            this.setUrl(url);
            this.setPassword(password);
            Class.forName(className);
            this.connex = DriverManager.getConnection(url, username, password);
        }
        catch(Exception e){
            throw e;
        }
    }
}

