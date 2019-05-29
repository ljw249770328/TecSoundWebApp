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
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;

/**
 *
 * @author Administrator
 */
public class GetUInfoServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户id和密码
            String id = request.getParameter("user_id").trim();

            Map<String,JSONArray> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            JSONArray users = GetUInfo(id);
            params.put("users",users);
            jsonObject.put("params", params);
            out.write(jsonObject.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    private JSONArray GetUInfo(String userId) {
        return UserDao.queryUser(userId);
    }

}
