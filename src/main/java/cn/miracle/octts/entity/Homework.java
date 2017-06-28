package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import java.sql.Date;


/**
 * Created by Tony on 2017/6/27.
 */
public class Homework extends BaseEntity{
    private Integer homework_id;
    private Integer course_id;
    private String teacher_id;
    private String status;
    private String title;
    private String message;
    private Date start_time;
    private Date end_time;
    private Integer resubmit_limit;
    private Date resubmit_limit_time;

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

    public void setResubmit_limit(Integer resubmit_limit) {
        this.resubmit_limit = resubmit_limit;
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

    public Integer getResubmit_limit() {
        return resubmit_limit;
    }

    public Date getResubmit_limit_time() {
        return resubmit_limit_time;
    }

    public void setResubmit_limit_time(Date resubmit_limit_time) {
        this.resubmit_limit_time = resubmit_limit_time;
    }
}
