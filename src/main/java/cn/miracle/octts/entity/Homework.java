package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * Created by Tony on 2017/6/27.
 */
public class Homework extends BaseEntity {
    private Integer homework_id;
    private Integer course_id;
    private String teacher_id;
    private Integer homework_score;
    private Integer homework_status;
    private String homework_title;
    private String homework_message;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date homework_start_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date homework_end_time;

    private Integer resubmit_limit;


    public Homework() {
    }


    public Integer getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(Integer homework_id) {
        this.homework_id = homework_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Integer getHomework_score() {
        return homework_score;
    }

    public void setHomework_score(Integer homework_score) {
        this.homework_score = homework_score;
    }

    public Integer getHomework_status() {
        return homework_status;
    }

    public void setHomework_status(Integer homework_status) {
        this.homework_status = homework_status;
    }

    public String getHomework_title() {
        return homework_title;
    }

    public void setHomework_title(String homework_title) {
        this.homework_title = homework_title;
    }

    public String getHomework_message() {
        return homework_message;
    }

    public void setHomework_message(String homework_message) {
        this.homework_message = homework_message;
    }

    public Date getHomework_start_time() {
        return homework_start_time;
    }

    public void setHomework_start_time(Date homework_start_time) {
        this.homework_start_time = homework_start_time;
    }

    public Date getHomework_end_time() {
        return homework_end_time;
    }

    public void setHomework_end_time(Date homework_end_time) {
        this.homework_end_time = homework_end_time;
    }

    public Integer getResubmit_limit() {
        return resubmit_limit;
    }

    public void setResubmit_limit(Integer resubmit_limit) {
        this.resubmit_limit = resubmit_limit;
    }
}
