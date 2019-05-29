/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.WebApp.TecSoundClass.Course;
import net.WebApp.TecSoundClass.DBManager;
import net.WebApp.TecSoundClass.Dao.CourseDao;
import net.WebApp.TecSoundClass.Dao.UserDao;
import net.WebApp.TecSoundClass.Radomnum;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Administrator
 */
public class Base642fileServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的信息
            String Base64file = request.getParameter("file");
            String kinds =request.getParameter("kinds");
            String path =request.getParameter("path");
            String table="";
            String tablekey="";
            if (request.getParameter("table")!=null) {
                table=request.getParameter("table");
                if (request.getParameter("tablekey")!=null) {
                    tablekey=request.getParameter("tablekey");
                }  
                if (table.equals("user")) {
                    UserDao.UpdateTable(tablekey);
                }
                if(table.equals("course")){
                    CourseDao.UpdateTable(tablekey);
                }
            }
            
//            if(kinds.equals("images")){
//                path="C:\\apache-tomcat-8.0.44\\webapps\\images\\"+path;
//            }if(kinds.equals("sound")){
//                path="C:\\apache-tomcat-8.0.44\\webapps\\sound\\"+path;
//            }else{
//                path="C:\\apache-tomcat-8.0.44\\webapps\\"+kinds+"\\"+path;
//            }
            File file =new File("C:\\apache-tomcat-8.0.44\\webapps\\"+kinds);
            if (!file.exists()) {
                file.mkdirs();
            }
            
            //调用函数
            String result=generateFile(Base64file,kinds,path);
            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("Result", result);
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * 验证是否匹配
     *
     * @param userName
     * @param realname
     * 
     */
    public static String generateFile(String fileStr, String kinds,String path) {
        String fullpath="C:\\apache-tomcat-8.0.44\\webapps\\"+kinds+"\\"+path;
        if (fileStr == null) {
            return "null";
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // 解密
            byte[] b = decoder.decodeBuffer(fileStr);
            // 处理数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            
            OutputStream out = new FileOutputStream(fullpath);
            out.write(b);
            out.flush();
            out.close();
            return "http://101.132.71.111:8080/"+kinds+"/"+path;
        } catch (Exception e) {
            return e.toString();
        }
    }

}