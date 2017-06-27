package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import java.util.Date;
import java.sql.Date;
import java.math.BigDecimal;

/**
 * Created by Tony on 2017/6/27.
 */
public class Course {
    private String course_id;
    private Date course_start_time;
    private Date course_end_time;
    private Integer course_hours;
    private BigDecimal credit;
    private String course_location;
    private String team_limit_information;
    private String teacher_information;
    private String course_information;

    public Course() {
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public Date getCourse_start_time() {
        return course_start_time;
    }

    public void setCourse_start_time(Date course_start_time) {
        this.course_start_time = course_start_time;
    }

    public Date getCourse_end_time() {
        return course_end_time;
    }

    public void setCourse_end_time(Date course_end_time) {
        this.course_end_time = course_end_time;
    }

    public Integer getCourse_hours() {
        return course_hours;
    }

    public void setCourse_hours(Integer course_hours) {
        this.course_hours = course_hours;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
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
