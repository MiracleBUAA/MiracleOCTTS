package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import java.util.Date;
import java.sql.Date;


/**
 * Created by Tony on 2017/6/27.
 */
public class Announcement extends BaseEntity{
    private Integer announcement_id;
    private Integer course_id;
    private String teacher_id;
    private String announcement_title;
    private String announcement_message;

    public Announcement() {
    }

    public Integer getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(Integer announcement_id) {
        this.announcement_id = announcement_id;
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

    public String getAnnouncement_title() {
        return announcement_title;
    }

    public void setAnnouncement_title(String announcement_title) {
        this.announcement_title = announcement_title;
    }

    public String getAnnouncement_message() {
        return announcement_message;
    }

    public void setAnnouncement_message(String announcement_message) {
        this.announcement_message = announcement_message;
    }
}
