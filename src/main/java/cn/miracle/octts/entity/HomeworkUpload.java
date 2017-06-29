package cn.miracle.octts.entity;


import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/29.
 */
public class HomeworkUpload extends BaseEntity {
    private Integer homework_upload_id;
    private Integer rcourse_id;
    private Integer homework_id;
    private Integer group_id;
    private String homework_url;
    private String message;
    private String homework_upload_time;

    public HomeworkUpload() {
    }

    public Integer getHomework_upload_id() {
        return homework_upload_id;
    }

    public void setHomework_upload_id(Integer homework_upload_id) {
        this.homework_upload_id = homework_upload_id;
    }

    public Integer getRcourse_id() {
        return rcourse_id;
    }

    public void setRcourse_id(Integer rcourse_id) {
        this.rcourse_id = rcourse_id;
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

    public String getHomework_url() {
        return homework_url;
    }

    public void setHomework_url(String homework_url) {
        this.homework_url = homework_url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getHomework_upload_time() {
        return homework_upload_time;
    }

    public void setHomework_upload_time(String homework_upload_time) {
        this.homework_upload_time = homework_upload_time;
    }
}
