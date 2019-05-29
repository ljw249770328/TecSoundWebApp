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
import net.WebApp.TecSoundClass.Dao.UserDao;
import net.WebApp.TecSoundClass.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class AlterUInfoServlet extends HttpServlet {
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            String id = request.getParameter("user_id").trim();
            String age= request.getParameter("user_age").trim();
            String sex= request.getParameter("user_sex").trim();
            String head_pic_src=request.getParameter("user_pic_src");
            //修改结果
            String verifyResult = AlterUInfo(id,age,sex,head_pic_src);

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("Result",verifyResult);
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

  
    private String AlterUInfo(String userid,String age,String sex,String src) {
        JSONArray users= UserDao.queryUser(userid);
        User user =new User();
        for(int i=0;i<users.size();i++){
            JSONObject obj=users.getJSONObject(i);
            
            user=(User) JSONObject.toBean(obj,User.class);
        }
        user.setUser_age(age);
        user.setUser_sex(sex);
        user.setUser_pic_src(src);
        return UserDao.AlterUser(user);
    }
}