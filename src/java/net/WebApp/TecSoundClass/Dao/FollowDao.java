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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.WebApp.TecSoundClass.DBManager;
import net.WebApp.TecSoundClass.Follow;
import net.WebApp.TecSoundClass.Point;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class FollowDao {
    
    public static JSONArray queryMyFollower(String UserId) {
        JSONArray follows=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("select follow.* from follow where follow_user_id=?");
           
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, UserId);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject follow=new JSONObject();
                follow.put("follow_id",resultSet.getString("follow_id"));
                follow.put("follow_user_id",resultSet.getString("follow_user_id"));
                follow.put("fan_user_id", resultSet.getString("fan_user_id"));
                follow.put("follow_time",resultSet.getString("follow_time"));
                follows.add(follow);
            }
            return follows;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    public static String insertFollower(Follow follow){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into follow(follow_id,follow_user_id,fan_user_id,follow_time)values(?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,UUID.randomUUID().toString());
            preparedStatement.setString(2,follow.getFollower_user_id());
            preparedStatement.setString(3,follow.getFan_user_id());
            preparedStatement.setString(4, follow.getFollow_time());

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
    
    
//不存在修改该表
    
    
    
    public static String DeleteFollow(String Fid){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM follow WHERE follow_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,Fid);
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
   
}
