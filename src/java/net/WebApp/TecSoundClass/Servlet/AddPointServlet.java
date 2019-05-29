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
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.WebApp.TecSoundClass.Dao.PointDao;
import net.WebApp.TecSoundClass.Dao.SignDao;
import net.WebApp.TecSoundClass.Point;
import net.WebApp.TecSoundClass.Sign;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class AddPointServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
            Point point =new Point();
            point.setPoint_id(UUID.randomUUID().toString());
            point.setRelease_course_id(request.getParameter("course_id"));
            point.setPoint_voice_src(request.getParameter("voice_url"));
            point.setPoint_content(request.getParameter("content"));
            //调用查询
            String result =AddPoint(point);
            JSONObject jsonObject = new JSONObject();
            Map<String,String> params = new HashMap<>();
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
    private String AddPoint(Point point) {
        return PointDao.insertPoint(point);
    }
}