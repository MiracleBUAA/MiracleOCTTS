package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by hf on 2017/7/1.
 */
public class GroupApply extends BaseEntity {

    private Integer group_apply_id;
    private Integer course_id;
    private String group_apply_name;
    private String group_apply_owner_id;

    public GroupApply() {
    }

    public Integer getGroup_apply_id() {
        return group_apply_id;
    }

    public void setGroup_apply_id(Integer group_apply_id) {
        this.group_apply_id = group_apply_id;
    }

    public Integer getCourse_id() {
        return course_id;
    }

    public void setCourse_id(Integer course_id) {
        this.course_id = course_id;
    }

    public String getGroup_apply_name() {
        return group_apply_name;
    }

    public void setGroup_apply_name(String group_apply_name) {
        this.group_apply_name = group_apply_name;
    }

    public String getGroup_apply_owner_id() {
        return group_apply_owner_id;
    }

    public void setGroup_apply_owner_id(String group_apply_owner_id) {
        this.group_apply_owner_id = group_apply_owner_id;
    }
}
