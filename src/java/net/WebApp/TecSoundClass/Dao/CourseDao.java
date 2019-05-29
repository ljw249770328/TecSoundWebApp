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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.WebApp.TecSoundClass.Course;
import net.WebApp.TecSoundClass.DBManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class CourseDao {
    public static List querykind(String Cid){
        List list =new ArrayList();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT course_id FROM course WHERE course_name like (SELECT course_name FROM course WHERE course_id =?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Cid);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                 
                list.add(resultSet.getString("course_id"));
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static JSONArray querycourse(String course_id) {
        JSONArray courses=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM course WHERE course_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, course_id);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject course=new JSONObject();
                course.put("course_id",resultSet.getString("course_id"));
                course.put("teacher_user_id",resultSet.getString("teacher_user_id"));
                course.put("course_class",resultSet.getString("course_class"));
                course.put("course_time",resultSet.getString("course_time"));
                course.put("register_time", resultSet.getString("register_time"));
                course.put("course_request",resultSet.getString("course_request"));
                course.put("course_pic_src",resultSet.getString("course_pic_src"));
                course.put("course_name",resultSet.getString("course_name"));
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
    public static JSONArray queryBelongcourse(String Tid) {
        JSONArray courses=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM course WHERE teacher_user_id=? ORDER BY course_name ");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Tid);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject course=new JSONObject();
                course.put("course_id",resultSet.getString("course_id"));
                course.put("teacher_user_id",resultSet.getString("teacher_user_id"));
                course.put("course_class",resultSet.getString("course_class"));
                course.put("course_time",resultSet.getString("course_time"));
                course.put("course_request",resultSet.getString("course_request"));
                course.put("course_pic_src",resultSet.getString("course_pic_src"));
                course.put("course_name",resultSet.getString("course_name"));
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
    public static JSONArray queryExistCourse(String Tid,String Cname) {
        JSONArray courses=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("SELECT * FROM course WHERE teacher_user_id=? and course_name=? ");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, Tid);
            preparedStatement.setString(2, Cname);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject course=new JSONObject();
                course.put("course_id",resultSet.getString("course_id"));
                course.put("teacher_user_id",resultSet.getString("teacher_user_id"));
                course.put("course_class",resultSet.getString("course_class"));
                course.put("course_pic_src",resultSet.getString("course_pic_src"));
                course.put("course_time",resultSet.getString("course_time"));
                course.put("course_name",resultSet.getString("course_name"));
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
    public static String insertCourse(Course course){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into course(course_id,teacher_user_id,course_class,course_name,course_time,course_request,course_pic_src)values(?,?,?,?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,course.getCourse_id());
            preparedStatement.setString(2,course.getTeacher_user_id());
            preparedStatement.setString(3,course.getCourse_class());
            preparedStatement.setString(4,course.getCourse_name());
            preparedStatement.setString(5,course.getCourse_time());
            preparedStatement.setString(6,course.getCourse_request());
            preparedStatement.setString(7, course.getCourse_pic_src());
  
            lines = preparedStatement.executeUpdate();
            return  course.getCourse_id();
        }catch(SQLIntegrityConstraintViolationException e){
            return "0";   
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static String AlterCourse(Course course){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE course set teacher_user_id=?,course_class=?,course_name=?,course_time=? ,course_request=? WHERE course_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,course.getTeacher_user_id());
            preparedStatement.setString(2,course.getCourse_class());
            preparedStatement.setString(3,course.getCourse_name());
            preparedStatement.setString(4,course.getCourse_time());
            preparedStatement.setString(5,course.getCourse_request());
            preparedStatement.setString(6,course.getCourse_id());
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    public static String DeleteCourse(String Course_id){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM course  WHERE course_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,Course_id);
            lines = preparedStatement.executeUpdate();
            return  "删除成功";
             
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
        sqlStatement.append("UPDATE course SET update_time=NOW() WHERE course_id=?");
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
