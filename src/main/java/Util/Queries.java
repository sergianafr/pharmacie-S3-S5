package Util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DbUtils.Connect;

public class Queries {
    public static List<Object> filtrer(Connect c, Object o, String nomTable) throws Exception {
        String query = (String)MapUtil.getSelectQuery(o, nomTable).get("query");
        System.out.println(query);

        try {
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(query);
            MapUtil.setStatement(preparedStatement, o);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<Object> results = new ArrayList<Object>();
            while(rs.next()){
                Object Object = MapUtil.mapResultSet(rs, o.getClass());
                results.add(Object);
            }
            preparedStatement.close();
            rs.close();
            return results;
            
        } catch (Exception e) {
            e.printStackTrace();
            c.closeBD();
            throw e;
        }
    }

     public static void insert (Connect con, Object obj, String nomTable) throws Exception{
        Connection co = null;
        try {
            co = con.getConnex();
            Field[] fields = obj.getClass().getDeclaredFields();
            String Query = "INSERT INTO "+nomTable+" VALUES (default";
            for (int i = 1; i < fields.length; i++) {
                Query += ", ?";
            }
            Query += ")";
            System.out.println(Query);
            PreparedStatement preparedStatement = co.prepareStatement(Query);
            for (int i = 1; i < fields.length; i++) {
                Method m=null;
                Object attr=null;
                m=obj.getClass().getMethod("get"+(fields[i].getName().substring(0,1).toUpperCase()+fields[i].getName().substring(1)), null);
                attr=m.invoke(obj,null); 
                preparedStatement.setObject(i, attr);
            }
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            throw new Exception(e.getMessage() + " passage InsertPrroduit");
        }

    }

    public static void delete(Connect c, int id, String nomTable)throws Exception{
        try {      
            String Query = "DELETE FROM ? WHERE id = ?";
            PreparedStatement preparedStatement = c.getConnex().prepareStatement(Query);
            preparedStatement.setString(1, nomTable);
            preparedStatement.setInt(2 , id);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            c.getConnex().commit();
        } catch (Exception e) {
            c.closeBD();
            throw e;
        }
    }
}
