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
import net.WebApp.TecSoundClass.Dao.CourseDao;
import net.WebApp.TecSoundClass.Dao.SelectiveDao;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class ExitCourseServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的信息
            String Cid = request.getParameter("Cid").trim();
            String Sid = request.getParameter("Sid").trim();
            
            
            //调用函数
            String Result = ExitClass(Cid,Sid);

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("Result",Result);
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
    private String ExitClass(String Cid,String Sid) {
        return SelectiveDao.ExitCourse(Sid, Cid);
    }
}
