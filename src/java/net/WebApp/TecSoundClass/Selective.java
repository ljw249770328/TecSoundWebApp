/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.WebApp.TecSoundClass;

import java.util.UUID;

/**
 *
 * @author Administrator
 */
public class Selective {
    String course_id="",join_user_id="",join_time="",selective_id="";

     public String getSelective_id() {
        return selective_id;
    }

    public void setSelective_id(String selective_id) {
        this.selective_id = selective_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getJoin_user_id() {
        return join_user_id;
    }

    public void setJoin_user_id(String join_user_id) {
        this.join_user_id = join_user_id;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }
}
