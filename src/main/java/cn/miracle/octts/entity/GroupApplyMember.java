package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by hf on 2017/7/1.
 */
public class GroupApplyMember extends BaseEntity {
    private Integer group_apply_id;
    private Integer course_id;
    private String student_id;
    private Integer group_role;

    public GroupApplyMember() {
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

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Integer getGroup_role() {
        return group_role;
    }

    public void setGroup_role(Integer group_role) {
        this.group_role = group_role;
    }
}
