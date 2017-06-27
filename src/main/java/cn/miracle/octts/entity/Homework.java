package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import java.sql.Date;


/**
 * Created by Tony on 2017/6/27.
 */
public class Homework extends BaseEntity{
    private String homework_id;
    private String course_id;
    private String teacher_id;
    private String status;
    private String title;
    private String message;
    private Date start_time;
    private Date end_time;
    private String resubmit_limit;
    private Date resubmit_limit_time;

    public Homework() {
    }

    public String getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(String homework_id) {
        this.homework_id = homework_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public String getResubmit_limit() {
        return resubmit_limit;
    }

    public void setResubmit_limit(String resubmit_limit) {
        this.resubmit_limit = resubmit_limit;
    }

    public Date getResubmit_limit_time() {
        return resubmit_limit_time;
    }

    public void setResubmit_limit_time(Date resubmit_limit_time) {
        this.resubmit_limit_time = resubmit_limit_time;
    }
}
