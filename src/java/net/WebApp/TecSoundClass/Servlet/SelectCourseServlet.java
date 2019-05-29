/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.WebApp.TecSoundClass.Course;
import net.WebApp.TecSoundClass.Dao.CourseDao;
import net.WebApp.TecSoundClass.Dao.SelectiveDao;
import net.WebApp.TecSoundClass.Radomnum;
import net.WebApp.TecSoundClass.Selective;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class SelectCourseServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的基本信息
            String Sid = request.getParameter("Sid").trim();
            String Cid = request.getParameter("Cid").trim();
            
            //调用存储函数
            String verifyResult = JoinClass(Sid, Cid);

            Map<String, String> params = new HashMap<>();
            JSONObject jsonObject = new JSONObject();
            params.put("Result", verifyResult);
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
    private String JoinClass(String Sid, String Cid) {
        //检查是否已选该课堂
        
        JSONArray courses=SelectiveDao.querySelectedCourse(Sid);
        for(int i=0;i<courses.size();i++){
            if(courses.getJSONObject(i).getString("course_class").equals(Cid)){
                return "exists";
            }
        }    
        //检查是否加入该课堂的其他班级
        List KindList =new ArrayList();
        KindList=CourseDao.querykind(Cid);
        for(int i=0;i<courses.size();i++)
            for(int j=0;j<KindList.size();j++){
                if(courses.getJSONObject(i).getString("course_id").equals(KindList.get(j))){
                    return "abandon";
                }
            }
        Selective selective =new Selective();
        selective.setJoin_user_id(Sid);
        selective.setCourse_id(Cid);
        
        //存入数据库,获取返回结果
        return SelectiveDao.insertSelective(selective);
    }
}
