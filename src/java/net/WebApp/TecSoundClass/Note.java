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
public class Note {
     private String note_user_id="",note_content="",note_voice_src="",note_pic_src="",note_time_src="";

    public String getNote_user_id() {
        return note_user_id;
    }

    public void setNote_user_id(String note_user_id) {
        this.note_user_id = note_user_id;
    }

    public String getNote_content() {
        return note_content;
    }

    public void setNote_content(String note_content) {
        this.note_content = note_content;
    }

    public String getNote_voice_src() {
        return note_voice_src;
    }

    public void setNote_voice_src(String note_voice_src) {
        this.note_voice_src = note_voice_src;
    }

    public String getNote_pic_src() {
        return note_pic_src;
    }

    public void setNote_pic_src(String note_pic_src) {
        this.note_pic_src = note_pic_src;
    }

    public String getNote_time_src() {
        return note_time_src;
    }

    public void setNote_time_src(String note_time_src) {
        this.note_time_src = note_time_src;
    }
}
