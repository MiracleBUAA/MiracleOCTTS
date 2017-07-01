package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by hf on 2017/6/25.
 */
public class Student extends BaseEntity {

//    private static final int PASSWORD_MAX_LENGTH = 30;
//    private static final int PASSWORD_MIN_LENGTH = 6;
//    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
//    private static final String PHONE_PATTERN = "^(13[0-9]|14[57]|15[012356789]|17[0678]|18[0-9])[0-9]{8}$";

    private String student_id;
    private Integer group_id;
    private String password;
    private String student_name;
    private String student_gender;
    private String student_class;
    private Integer student_absent;
    private Double student_rate;
    private Double personal_score;
    private Double group_score;

    public Student() {
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Integer getGroup_id() {
        return group_id;
    }

    public void setGroup_id(Integer group_id) {
        this.group_id = group_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_gender() {
        return student_gender;
    }

    public void setStudent_gender(String student_gender) {
        this.student_gender = student_gender;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public Integer getStudent_absent() {
        return student_absent;
    }

    public void setStudent_absent(Integer student_absent) {
        this.student_absent = student_absent;
    }

    public Double getStudent_rate() {
        return student_rate;
    }

    public void setStudent_rate(Double student_rate) {
        this.student_rate = student_rate;
    }

    public Double getPersonal_score() {
        return personal_score;
    }

    public void setPersonal_score(Double personal_score) {
        this.personal_score = personal_score;
    }

    public Double getGroup_score() {
        return group_score;
    }

    public void setGroup_score(Double group_score) {
        this.group_score = group_score;
    }
}
