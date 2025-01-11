/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author SERGIANA
 */
public class MapUtil {
    public static int getNbAttr(Class classe){
        int nb=0;
        try{
            Field[] attribut=classe.getDeclaredFields();
            if(attribut.length==0){
                Class<?> mere=classe.getSuperclass();
                attribut=mere.getDeclaredFields();
            }
            nb=attribut.length;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return nb;
    }
    public static LocalDate getDate(String date)
    {
        String[] un=date.split(" ");
        String[] misaraka=(un[0]).split("-");
        int day=(int)Integer.parseInt(misaraka[2]);
        int month=(int)Integer.parseInt(misaraka[1]);
        int year=(int)Integer.parseInt(misaraka[0]);
        LocalDate d=LocalDate.of(year,month,day);
        //d.set(year, month, day);
        return d;
    }
    public static LocalDateTime toLocalDateTime(String date){
        String[] un=date.split(" ");
        String[] m1=(un[0]).split("-");
        int day=(int)Integer.parseInt(m1[2]);
        int month=(int)Integer.parseInt(m1[1]);
        int year=(int)Integer.parseInt(m1[0]);
        String[] m2=(un[1]).split(":");
        int hour=Integer.parseInt(m2[0]);
        int min=Integer.parseInt(m2[1]);
        LocalDateTime d=LocalDateTime.of(year, month, day, hour, min);
        return d;
    }
    public static String transformLocalDateTime(LocalDateTime l){
        String sql="";
        sql+=+l.getYear()+"-"+l.getMonthValue()+"-"+l.getDayOfMonth();
        sql+=" "+l.getHour()+":"+l.getMinute()+":"+l.getSecond();
        return sql;
    }
    public static int boolToInt(boolean b){
        if (b){
            return 1;
        }
        else{
            return 0;
        }
    }
    public static boolean getBooleanValue(String b){
        if(b.equals("1")){
            return true;
        }
        else if(b.equals("0")){
            return false;
        }
        else{
            return Boolean.valueOf(b);
        }
    }
    
    public static HashMap<String, Object> getSelectQuery(Object o, String nomTable) throws Exception{
        String query = "SELECT * FROM "+nomTable+" WHERE 1=1";
        int nbField = 0;
        try{
            Field[] attributs=o.getClass().getFields();
            for(Field field : attributs){
                if(!field.getName().equals("nomTable")){
                    Method m=null;
                    Object attr=null;
                    m=o.getClass().getMethod("get"+(field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)), null);
                    attr=m.invoke(o,null); 
                    if (attr != null) {
                        // Vérifier pour les chaînes : éviter les null, chaînes vides ou espaces uniquement
                        if (field.getType().getSimpleName().equals("String") && attr instanceof String && !((String) attr).isEmpty()) {
                            query += " AND " + MapUtil.toSnakeCase(field.getName()) + " LIKE ? ";
                            nbField++;
                        } 
                        // Vérifier pour les nombres : éviter null et 0
                        else if (Number.class.isAssignableFrom(field.getType()) && attr instanceof Number && ((Number) attr).doubleValue() != 0) {
                            query += " AND " + MapUtil.toSnakeCase(field.getName()) + " = ? ";
                            nbField++;
                        }
                        // Ajouter d'autres types si nécessaire, comme LocalDate ou Boolean
                        else if (!field.getType().getSimpleName().equals("String") && attr != null) {
                            query += " AND " + MapUtil.toSnakeCase(field.getName()) + " = ? ";
                            nbField++;
                        }
                    }
                    
                }
            }
        } catch(Exception e)   {
            throw e;
        }
        HashMap<String,Object> val = new HashMap<String,Object>();
        val.put("query", query);
        val.put("nbField", nbField);
        return val;
    }

    public static String toSnakeCase(String camelCase) {
        // Remplace chaque majuscule par un underscore suivi de la lettre en minuscule
        return camelCase.replaceAll("([a-z])([A-Z])", "$1_$2").toLowerCase();
    }
    public static void setStatement(PreparedStatement statement, Object o)throws Exception{
        int count = 1;
        try{
            Field[] attributs=o.getClass().getFields();
            for(Field field : attributs){
                if(!field.getName().equals("nomTable")){
                    Method m=null;
                    Object attr=null;
                    m=o.getClass().getMethod("get"+(field.getName().substring(0,1).toUpperCase()+field.getName().substring(1)), null);
                    attr=m.invoke(o,null); 
                    if(attr!=null){
                        if (attr != null) {
                            if(field.getType().getSimpleName().equals("String") && !((String)attr).isEmpty()) {
                                statement.setString(count, "%"+attr+"%");
                            }
                            else{
                                statement.setObject(count, attr);
                            }
                            count++;
                        }
                    }
                }
            }
            
        } catch(Exception e){
            throw e;
        }
        
    }

    public static <T> T mapResultSet(ResultSet rs, Class<T> clazz) throws Exception {
        // Vérifier s'il y a un résultat
        if (!rs.next()) {
            return null; // Retourner null si le ResultSet est vide
        }

        // Récupérer tous les constructeurs de la classe
        Constructor<?>[] constructors = clazz.getConstructors();

        // Trouver un constructeur qui correspond au nombre de colonnes
        Constructor<?> targetConstructor = null;
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == rs.getMetaData().getColumnCount()) {
                targetConstructor = constructor;
                break;
            }
        }

        if (targetConstructor == null) {
            throw new IllegalArgumentException("Aucun constructeur compatible trouvé pour la classe " + clazz.getName());
        }

        // Préparer les paramètres pour le constructeur
        Object[] params = new Object[targetConstructor.getParameterCount()];
        for (int i = 0; i < params.length; i++) {
            params[i] = rs.getObject(i + 1); // Les index de ResultSet commencent à 1
        }

        // Créer une instance de la classe avec le constructeur trouvé
        @SuppressWarnings("unchecked")
        T instance = (T) targetConstructor.newInstance(params);
        return instance;
    }
}
