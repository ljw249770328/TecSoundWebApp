/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass;

/**
 *
 * @author Administrator
 */
public class Point {
    private String point_id="",release_course_id="",point_time="",point_voice_src="",point_content="";
     
    public String getPoint_content() {
        return point_content;
    }

    public void setPoint_content(String point_content) {
        this.point_content = point_content;
    }

    public String getPoint_id() {
        return point_id;
    }

    public void setPoint_id(String point_id) {
        this.point_id = point_id;
    }

    public String getRelease_course_id() {
        return release_course_id;
    }

    public void setRelease_course_id(String release_course_id) {
        this.release_course_id = release_course_id;
    }

    public String getPoint_time() {
        return point_time;
    }

    public void setPoint_time(String point_time) {
        this.point_time = point_time;
    }

    public String getPoint_voice_src() {
        return point_voice_src;
    }

    public void setPoint_voice_src(String point_voice_src) {
        this.point_voice_src = point_voice_src;
    }
}