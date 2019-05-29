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
import net.WebApp.TecSoundClass.Selective;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class SelectiveDao {

    public static JSONArray querySelectedCourse(String Sid) {
        JSONArray courses=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("select course.* from  course join selective on course.course_id = selective.selective_course_id and join_user_id=? ORDER BY course_name");
           
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Sid);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject course=new JSONObject();
                course.put("course_id",resultSet.getString("course_id"));
                course.put("teacher_user_id",resultSet.getString("teacher_user_id"));
                course.put("course_class",resultSet.getString("course_class"));
                course.put("course_time",resultSet.getString("course_time"));
                course.put("course_request",resultSet.getString("course_request"));
                course.put("course_name",resultSet.getString("course_name"));
                course.put("course_pic_src",resultSet.getString("course_pic_src"));
                course.put("update_time",resultSet.getString("update_time"));
                courses.add(course);
            }
            return courses;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    public static String insertSelective(Selective selective){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into selective(selective_id,selective_course_id,join_user_id)values(?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
             preparedStatement.setString(1,UUID.randomUUID().toString());
            preparedStatement.setString(2,selective.getCourse_id());
            preparedStatement.setString(3,selective.getJoin_user_id());
  
            lines = preparedStatement.executeUpdate();
            return "加入成功";
        }catch(SQLIntegrityConstraintViolationException e){
            return "0";   
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
//    不存在学生修改选课表功能
//    public static String AlterCourse(Course course){
//        //获得数据库的连接对象
//        Connection connection = DBManager.getConnection();
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet = null;
//        int lines=0;
//
//        //生成SQL代码
//        StringBuilder sqlStatement = new StringBuilder();
//        sqlStatement.append("UPDATE course set teacher_user_id,course_class,course_name,course_time ,course_request WHERE course_id=?");
//
//        //设置数据库的字段值
//        try {
//            preparedStatement = connection.prepareStatement(sqlStatement.toString());
//            
//            preparedStatement.setString(1,course.getTeacher_user_id());
//            preparedStatement.setString(2,course.getCourse_class());
//            preparedStatement.setString(3,course.getCourse_name());
//            preparedStatement.setString(4,course.getCourse_time());
//            preparedStatement.setString(5,course.getCourse_request());
//            preparedStatement.setString(6,course.getCourse_id());
//            lines = preparedStatement.executeUpdate();
//            return  "success";
//             
//        }catch (SQLException ex) {
//            return ex.toString();
//        } finally {
//            DBManager.closeAll(connection, preparedStatement, resultSet);
//        }
//    }
    
    
    
    public static String ExitCourse(String Sid,String courseId){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM selective  WHERE selective_course_id=? and join_user_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,courseId);
            preparedStatement.setString(2,Sid);
            lines = preparedStatement.executeUpdate();
            return  "退出成功";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
     public static String ClassDismiss(String courseId){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM selective  WHERE selective_course_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,courseId);
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
}
