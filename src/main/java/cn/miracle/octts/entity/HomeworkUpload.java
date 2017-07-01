package cn.miracle.octts.entity;


import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/29.
 */
public class HomeworkUpload extends BaseEntity {
    private Integer homework_upload_id;
    private Integer course_id;
    private Integer homework_id;
    private Integer group_id;
    private String file_name;
    private String homework_url;

    public HomeworkUpload() {
    }

    public Integer getHomework_upload_id() {
        return homework_upload_id;
    }

    public void setHomework_upload_id(Integer homework_upload_id) {
        this.homework_upload_id = homework_upload_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public Integer getHomework_id() {
        return homework_id;
    }

    public void setHomework_id(Integer homework_id) {
        this.homework_id = homework_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getHomework_url() {
        return homework_url;
    }

    public void setHomework_url(String homework_url) {
        this.homework_url = homework_url;
    }
}
