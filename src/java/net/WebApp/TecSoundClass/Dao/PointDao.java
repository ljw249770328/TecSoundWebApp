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
import net.WebApp.TecSoundClass.Point;
import net.WebApp.TecSoundClass.Sign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class PointDao {
    
    public static JSONArray queryClassPoint(String CourseId) {
        JSONArray points=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("select point.* from point where release_course_id=? ORDER BY point_time DESC");
           
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CourseId);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject point=new JSONObject();
                point.put("point_id",resultSet.getString("point_id"));
                point.put("release_course_id",resultSet.getString("release_course_id"));
                point.put("point_time", resultSet.getString("point_time"));
                point.put("point_voice_src",resultSet.getString("point_voice_src"));
                point.put("point_content",resultSet.getString("point_content"));
                points.add(point);
            }
            return points;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    public static String insertPoint(Point point){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into point(point_id,release_course_id,point_voice_src,point_content)values(?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,point.getPoint_id());
            preparedStatement.setString(2,point.getRelease_course_id());
            preparedStatement.setString(3, point.getPoint_voice_src());
            preparedStatement.setString(4, point.getPoint_content());

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
    
    
    public static String AlterSign(Point point){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE point set point_content=? WHERE point_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,point.getPoint_content());
            preparedStatement.setString(2,point.getPoint_id());
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
    
    public static String DeleteSign(String Pid){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM point  WHERE point_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,Pid);
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
   
}
