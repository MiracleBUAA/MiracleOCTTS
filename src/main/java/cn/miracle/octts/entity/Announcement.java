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
    private String announcement_id;
    private String course_id;
    private String teacher_id;
    private String title;
    private String message;
    private Date start_time;
    private Date end_time;

    public Announcement() {
    }

    public String getAnnouncement_id() {
        return announcement_id;
    }

    public void setAnnouncement_id(String announcement_id) {
        this.announcement_id = announcement_id;
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
}
