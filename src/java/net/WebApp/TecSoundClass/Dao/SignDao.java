/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.WebApp.TecSoundClass.DBManager;
import net.WebApp.TecSoundClass.Selective;
import net.WebApp.TecSoundClass.Sign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class SignDao {
 
    public static JSONArray queryMySign(String CId,String SId) {
        JSONArray signs=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("select sign.* from sign where sign_user_id=? and sign_course=? ORDER BY sign_time DESC");
           
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, SId);
            preparedStatement.setString(2, CId);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject sign=new JSONObject();
                sign.put("sign_user_id",resultSet.getString("sign_user_id"));
                sign.put("sign_course",resultSet.getString("sign_course"));
                sign.put("sign_address",resultSet.getString("sign_address"));
                sign.put("sign_time",resultSet.getString("sign_time"));
                sign.put("sign_state",resultSet.getString("sign_state"));
                sign.put("sign_voice_src",resultSet.getString("sign_voice_src"));
                sign.put("sign_facepic_src",resultSet.getString("sign_facepic_src"));
                signs.add(sign);
            }
            return signs;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    public static JSONArray querySign(String Cid){
        JSONArray signs =new JSONArray();
        Connection connection =DBManager.getConnection();
        PreparedStatement preparedStatement =null;
        ResultSet resultSet= null;
        
        
        StringBuilder sqlstqtement =new StringBuilder();
        sqlstqtement.append("select sign.* from sign where sign_course=? ORDER BY sign_time DESC");
        
        try {
            preparedStatement=connection.prepareCall(sqlstqtement.toString());
            preparedStatement.setString(1, Cid);
            
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject sign=new JSONObject();
                sign.put("sign_user_id",resultSet.getString("sign_user_id"));
                sign.put("sign_course",resultSet.getString("sign_course"));
                sign.put("sign_address",resultSet.getString("sign_address"));
                sign.put("sign_time",resultSet.getString("sign_time"));
                sign.put("sign_state",resultSet.getString("sign_state"));
                sign.put("sign_voice_src",resultSet.getString("sign_voice_src"));
                sign.put("sign_facepic_src",resultSet.getString("sign_facepic_src"));
                signs.add(sign);
            }
            return signs;
            
        } catch (SQLException ex) {
            Logger.getLogger(SignDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        
        
    }
    
    public static String insertSign(Sign sign){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into sign(sign_user_id,sign_course,sign_address,sign_state,sign_voice_src,sign_facepic_src)values(?,?,?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,sign.getSign_user_id());
            preparedStatement.setString(2,sign.getSign_course());
            preparedStatement.setString(3,sign.getSign_adress());
            preparedStatement.setString(4,sign.getSign_state());
            preparedStatement.setString(5,sign.getSign_voice_src());
            preparedStatement.setString(6,sign.getSign_pacepic_src());

  
            lines = preparedStatement.executeUpdate();
            return "success";
        }catch(SQLIntegrityConstraintViolationException e){
            return "0";   
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
    public static String AlterSign(Sign sign){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE sign set sign_state=？ WHERE sign_user_id=? and sign_course=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,sign.getSign_state());
            preparedStatement.setString(2,sign.getSign_user_id());
            preparedStatement.setString(3,sign.getSign_course());
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
    
    public static String DeleteSign(String Sid,String Cid){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM sign  WHERE sign_user_id=? and sign_course=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,Sid);
            preparedStatement.setString(2,Cid);
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
   
}
