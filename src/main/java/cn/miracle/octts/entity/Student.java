package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by hf on 2017/6/25.
 */
public class Student extends BaseEntity {

    private static final int PASSWORD_MAX_LENGTH = 30;
    private static final int PASSWORD_MIN_LENGTH = 6;
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String PHONE_PATTERN = "^(13[0-9]|14[57]|15[012356789]|17[0678]|18[0-9])[0-9]{8}$";

    private String student_id;
    @Size(max = PASSWORD_MAX_LENGTH, min = PASSWORD_MIN_LENGTH)
    private String password;

    private boolean gender;
    private String group_id;

    private String name;
    private String student_class;

    @Pattern(regexp = EMAIL_PATTERN)
    private String email;

    @Pattern(regexp = PHONE_PATTERN)
    private String telephone;

    public Student() {
    }

    public static int getPasswordMaxLength() {
        return PASSWORD_MAX_LENGTH;
    }

    public static int getPasswordMinLength() {
        return PASSWORD_MIN_LENGTH;
    }

    public static String getEmailPattern() {
        return EMAIL_PATTERN;
    }

    public static String getPhonePattern() {
        return PHONE_PATTERN;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }
}
