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
import net.WebApp.TecSoundClass.Dao.InteractDao;
import net.WebApp.TecSoundClass.Dao.PointDao;
import net.WebApp.TecSoundClass.Interaction;
import net.WebApp.TecSoundClass.Point;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class AddInteractServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
            Interaction interaction =new Interaction();
            interaction.setProblem_id(UUID.randomUUID().toString());
            interaction.setPropose_course_id(request.getParameter("propose_course_id"));
            interaction.setAnswer_user_id(request.getParameter("answer_user_id"));
            interaction.setProblem_content(request.getParameter("problem_content"));
            interaction.setProblem_content_src(request.getParameter("problem_content_src"));
            interaction.setAnswer_content(request.getParameter("answer_content").trim());
            interaction.setAnswer_content_src(request.getParameter("answer_content_src"));
            interaction.setAnswer_grade(request.getParameter("answer_grade"));
            //调用查询
            String result =AddInteract(interaction);
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

    private String AddInteract(Interaction interaction) {
        return InteractDao.insertPoint(interaction);
    }
}