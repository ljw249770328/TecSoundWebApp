/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.WebApp.TecSoundClass.Course;
import net.WebApp.TecSoundClass.Dao.CourseDao;
import net.WebApp.TecSoundClass.Dao.SelectiveDao;
import net.WebApp.TecSoundClass.Dao.UserDao;
import net.WebApp.TecSoundClass.Radomnum;
import net.WebApp.TecSoundClass.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 *
 * @author Administrator
 */
public class GetCListServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
            String id = request.getParameter("user_id").trim();
            //调用查询
            JSONArray courseList = queryClass(id);
            JSONObject jsonObject = new JSONObject();
            Map<String,JSONArray> params = new HashMap<>();
            params.put("courses", courseList);
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
    private JSONArray queryClass(String id) {
        //根据身份返回列表来源
        JSONObject obj=(JSONObject) UserDao.queryUser(id).get(0);
         User user=(User) JSONObject.toBean(obj,User.class);
        
        if (user.getUser_identity().equals("老师")) {
            return CourseDao.queryBelongcourse(id);
        }
        else{
            return SelectiveDao.querySelectedCourse(id);
        }
    }
}