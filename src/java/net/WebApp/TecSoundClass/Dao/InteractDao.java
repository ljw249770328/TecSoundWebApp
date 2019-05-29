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
import net.WebApp.TecSoundClass.Interaction;
import net.WebApp.TecSoundClass.Point;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class InteractDao {
   public static JSONArray queryClassInteract(String CourseId) {
        JSONArray interacts=new JSONArray();
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("select interaction.* from interaction where propose_course_id=? ORDER BY answer_time DESC");
           
        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1, CourseId);
            
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                JSONObject interact=new JSONObject();
                interact.put("problem_id", resultSet.getString("problem_id"));
                interact.put("propose_course_id", resultSet.getString("propose_course_id"));
                interact.put("answer_user_id",resultSet.getString("answer_user_id"));
                interact.put("problem_content", resultSet.getString("problem_content"));
                interact.put("problem_content_src", resultSet.getString("problem_content_src"));
                interact.put("answer_content", resultSet.getString("answer_content"));
                interact.put("answer_content_src", resultSet.getString("answer_content_src"));
                interact.put("answer_time", resultSet.getString("answer_time"));
                interact.put("answer_grade", resultSet.getString("answer_grade"));
                interacts.add(interact);
            }
            return interacts;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    public static String insertPoint(Interaction interact){
        //获得数据库的连接对象
        Connection connection=DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        int lines=100;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("INSERT into interaction(problem_id,propose_course_id,answer_user_id,problem_content,problem_content_src,answer_content,answer_content_src,answer_grade)values(?,?,?,?,?,?,?,?)");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            preparedStatement.setString(1,interact.getProblem_id());
            preparedStatement.setString(2,interact.getPropose_course_id());
            preparedStatement.setString(3,interact.getAnswer_user_id());
            preparedStatement.setString(4,interact.getProblem_content()); 
            preparedStatement.setString(5,interact.getProblem_content_src());
            preparedStatement.setString(6,interact.getAnswer_content()); 
            preparedStatement.setString(7,interact.getAnswer_content_src());
            preparedStatement.setString(8,interact.getAnswer_grade());

            lines = preparedStatement.executeUpdate();
            return "success";
        }catch(SQLIntegrityConstraintViolationException e){
            return e.toString();   
        } catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
    public static String AlterSign(Interaction interact){
        //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("UPDATE interaction set answer_grade=? WHERE problem_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,interact.getAnswer_grade());
            preparedStatement.setString(2,interact.getProblem_id());
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
    
    
    
    public static String DeleteSign(String InteractId){
     //获得数据库的连接对象
        Connection connection = DBManager.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int lines=0;

        //生成SQL代码
        StringBuilder sqlStatement = new StringBuilder();
        sqlStatement.append("DELETE FROM interaction  WHERE problem_id=?");

        //设置数据库的字段值
        try {
            preparedStatement = connection.prepareStatement(sqlStatement.toString());
            
            preparedStatement.setString(1,InteractId);
            lines = preparedStatement.executeUpdate();
            return  "success";
             
        }catch (SQLException ex) {
            return ex.toString();
        } finally {
            DBManager.closeAll(connection, preparedStatement, resultSet);
        }
    }
   
}
