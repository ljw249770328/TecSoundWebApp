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
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.WebApp.TecSoundClass.DBManager;
import net.WebApp.TecSoundClass.User;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;


/**
 *
 * @author Administrator
 */
public class UserDao {
    /**
     * 查询给定用户名的用户的详细信息
     *
     * @param user_id 给定的用户名
     * @return 查询到的封装了详细信息的User对象
     */
    public static JSONArray queryUser(String user_id) {
        JSONArray users=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM user WHERE user_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, user_id);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject user=new JSONObject();
                user.put("user_id",resultSet.getString("user_id"));
                user.put("user_password",resultSet.getString("user_password"));
                user.put("user_age",resultSet.getString("user_age"));
                user.put("user_identity",resultSet.getString("user_identity"));
                user.put("user_institution",resultSet.getString("user_institution"));
                user.put("user_sex",resultSet.getString("user_sex"));
                user.put("user_name",resultSet.getString("user_name"));
                user.put("user_pic_src",resultSet.getString("user_pic_src"));
                user.put("update_time",resultSet.getString("update_time"));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static String insertUser(User user){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into user(user_id,user_password,user_age,user_identity,user_institution,user_sex,user_name,user_pic_src)values(?,?,?,?,?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,user.getUser_id());
            preparedStatement.setString(2,user.getUser_password());
            preparedStatement.setString(3,user.getUser_age());
            preparedStatement.setString(4,user.getUser_identity());
            preparedStatement.setString(5,user.getUser_institution());
            preparedStatement.setString(6,user.getUser_sex());
            preparedStatement.setString(7,user.getUser_name());
            preparedStatement.setString(8,user.getUser_pic_src());
  
            lines = preparedStatement.executeUpdate();
            return  "success";
        }catch(SQLIntegrityConstraintViolationException e){
            return "0";   
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static String AlterUser(User user){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE user set user_password=?,user_age=?,user_identity=?,user_institution=?,user_sex=?,user_name=?,user_pic_src=? WHERE user_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,user.getUser_password());
            preparedStatement.setString(2,user.getUser_age());
            preparedStatement.setString(3,user.getUser_identity());
            preparedStatement.setString(4,user.getUser_institution());
            preparedStatement.setString(5,user.getUser_sex());
            preparedStatement.setString(6,user.getUser_name());
            preparedStatement.setString(7,user.getUser_pic_src());
            preparedStatement.setString(8,user.getUser_id());
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static void UpdateTable(String key){
        Connection connection =DBManager.getConnection();
        PreparedStatement preparedStatement =null;
        ResultSet resultSet =null;
        
        StringBuilder sqlStatement =new StringBuilder();
        sqlStatement.append("UPDATE user SET update_time=NOW() WHERE user_id=?");
        int lines=0;
        try {
            preparedStatement=connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, key);
            lines=preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.toString());
        }finally{
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
        
    }
}
