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
public class Interaction {
    private String propose_course_id="",answer_user_id="",answer_content="",answer_content_src="",answer_time="",problem_id="",answer_grade="",problem_content = "",problem_content_src = "";;

     public String getProblem_content() {
        return problem_content;
    }

    public void setProblem_content(String problem_content) {
        this.problem_content = problem_content;
    }

    public String getProblem_content_src() {
        return problem_content_src;
    }

    public void setProblem_content_src(String problem_content_src) {
        this.problem_content_src = problem_content_src;
    }
    
    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }

    public String getProblem_id() {
        return problem_id;
    }

    public void setProblem_id(String problem_id) {
        this.problem_id = problem_id;
    }

    public String getAnswer_grade() {
        return answer_grade;
    }

    public void setAnswer_grade(String answer_grace) {
        this.answer_grade = answer_grace;
    }

    public String getPropose_course_id() {
        return propose_course_id;
    }

    public void setPropose_course_id(String propose_course_id) {
        this.propose_course_id = propose_course_id;
    }

    public String getAnswer_user_id() {
        return answer_user_id;
    }

    public void setAnswer_user_id(String answer_user_id) {
        this.answer_user_id = answer_user_id;
    }

    public String getAnswer_content_src() {
        return answer_content_src;
    }

    public void setAnswer_content_src(String answer_content_src) {
        this.answer_content_src = answer_content_src;
    }

    public String getAnswer_time() {
        return answer_time;
    }

    public void setAnswer_time(String answer_time) {
        this.answer_time = answer_time;
    }
}