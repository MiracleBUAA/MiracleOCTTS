package cn.miracle.octts.entity;


import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/27.
 */
public class GroupConfirm extends BaseEntity {
    private Integer group_id;
    private Integer course_id;
    private String group_name;
    private String group_owner_id;
    private Double group_score;

    public GroupConfirm() {
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_owner_id() {
        return group_owner_id;
    }

    public void setGroup_owner_id(String group_owner_id) {
        this.group_owner_id = group_owner_id;
    }

    public Double getGroup_score() {
        return group_score;
    }

    public void setGroup_score(Double group_score) {
        this.group_score = group_score;
    }
}
