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

/**
 *
 * @author Administrator
 */
public class RegeditServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户注册基本信息
            String id = request.getParameter("user_id").trim();
            String password = request.getParameter("user_password").trim();
            String identity =request.getParameter("user_identity").trim();
            String name=request.getParameter("user_name").trim();
            

            //调用存储函数
            String verifyResult = verifyRegedit(id,password,identity,name);

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();

            if ("success".equals(verifyResult)) {
                params.put("Result", "success");
            } else {
                params.put("Result", verifyResult);
            }

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
    private String verifyRegedit(String userId, String password,String identity,String name) {
        //封装
        User user = new User();
        user.setUser_id(userId);
        user.setUser_password(password);
        user.setUser_identity(identity);
        user.setUser_name(name);
        //存入数据库,获取返回结果
        return UserDao.insertUser(user);
    }
}
