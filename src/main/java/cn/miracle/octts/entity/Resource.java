package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/27.
 */
public class Resource extends BaseEntity{
    private String resource_id;
    private String course_id;
    private String teacher_id;
    private String resource_type;
    private String title;
    private String resource_url;
    private String resource_size;
    private String last_update_time;

    public Resource() {
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
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

    public String getResource_type() {
        return resource_type;
    }

    public void setResource_type(String resource_type) {
        this.resource_type = resource_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResource_url() {
        return resource_url;
    }

    public void setResource_url(String resource_url) {
        this.resource_url = resource_url;
    }

    public String getResource_size() {
        return resource_size;
    }

    public void setResource_size(String resource_size) {
        this.resource_size = resource_size;
    }

    public String getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(String last_update_time) {
        this.last_update_time = last_update_time;
    }
}
