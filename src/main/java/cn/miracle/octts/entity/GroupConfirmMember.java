package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/29.
 */
public class GroupConfirmMember extends BaseEntity {
    private Integer group_id;
    private Integer course_id;
    private String student_id;
    private Integer group_role;

    public GroupConfirmMember() {
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
