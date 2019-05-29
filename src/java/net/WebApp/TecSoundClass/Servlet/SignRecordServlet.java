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
import net.WebApp.TecSoundClass.Dao.SignDao;
import net.WebApp.TecSoundClass.Sign;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author asus
 */
public class SignRecordServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {
            Sign sign=new Sign();
            sign.setSign_user_id(request.getParameter("sign_user_id").trim()); 
            sign.setSign_course(request.getParameter("sign_course").trim()); 
            sign.setSign_adress(request.getParameter("sign_address").trim());
            sign.setSign_state(request.getParameter("sign_state").trim());
            sign.setSign_voice_src(request.getParameter("sign_voice_src").trim()); 
            sign.setSign_pacepic_src(request.getParameter("sign_facepic_src").trim()); 
            //调用查询
            String result =RecordSign(sign);
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
    private String RecordSign(Sign sign) {
        return SignDao.insertSign(sign);
    }
}