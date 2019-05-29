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
import net.WebApp.TecSoundClass.Dao.UserDao;
import net.WebApp.TecSoundClass.Radomnum;
import net.WebApp.TecSoundClass.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author Administrator
 */
public class CreateClassServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 设置响应内容类型  
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        try (PrintWriter out = response.getWriter()) {

            //获得请求中传来的用户注册基本信息
            Course course=new Course();
            course.setCourse_id(request.getParameter("course_id").trim());
            course.setTeacher_user_id(request.getParameter("teacher_user_id").trim());
            course.setCourse_class(request.getParameter("course_class").trim());
            course.setCourse_name(request.getParameter("course_name").trim());
            course.setCourse_time(request.getParameter("course_time").trim());
            course.setCourse_request(request.getParameter("course_request").trim());
            if (!request.getParameter("course_pic_src").equals("")) {
                course.setCourse_pic_src(request.getParameter("course_pic_src").trim());
            }
            //调用存储函数
            String verifyResult = verifyCreate(course);

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
    private String verifyCreate(Course course) {
        //封装
        //查询该教师是否重复创建课程
        JSONArray courses=CourseDao.queryExistCourse(course.getTeacher_user_id(), course.getCourse_name());
        for(int i=0;i<courses.size();i++){
            if(courses.getJSONObject(i).getString("course_class").equals(course.getClass())){
                return "exists";
            }
        }
        //存入数据库,获取返回结果
        return CourseDao.insertCourse(course);
    }
}
