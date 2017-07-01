package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import java.util.Date;

//import java.util.Date;

/**
 * Created by Tony on 2017/6/27.
 */
public class Course extends BaseEntity {
    private Integer course_id;
    private Integer course_year;
    private Date course_start_time;
    private Integer course_status; // 课程状态，0为结束，1位开始
    private String course_name;
    private Integer course_hour;
    private Double course_credit;
    private String course_location;
    private String team_limit_information;
    private String teacher_information;
    private String course_information;

    public Course() {
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getCourse_year() {
        return course_year;
    }

    public void setCourse_year(Integer course_year) {
        this.course_year = course_year;
    }

    public Date getCourse_start_time() {
        return course_start_time;
    }

    public void setCourse_start_time(Date course_start_time) {
        this.course_start_time = course_start_time;
    }

    public Integer getCourse_status() {
        return course_status;
    }

    public void setCourse_status(Integer course_status) {
        this.course_status = course_status;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Integer getCourse_hour() {
        return course_hour;
    }

    public void setCourse_hour(Integer course_hour) {
        this.course_hour = course_hour;
    }

    public Double getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit(Double course_credit) {
        this.course_credit = course_credit;
    }

    public String getCourse_location() {
        return course_location;
    }

    public void setCourse_location(String course_location) {
        this.course_location = course_location;
    }

    public String getTeam_limit_information() {
        return team_limit_information;
    }

    public void setTeam_limit_information(String team_limit_information) {
        this.team_limit_information = team_limit_information;
    }

    public String getTeacher_information() {
        return teacher_information;
    }

    public void setTeacher_information(String teacher_information) {
        this.teacher_information = teacher_information;
    }

    public String getCourse_information() {
        return course_information;
    }

    public void setCourse_information(String course_information) {
        this.course_information = course_information;
    }
}
