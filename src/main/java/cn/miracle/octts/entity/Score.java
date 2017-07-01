package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/6/29.
 */
public class Score extends BaseEntity {
    private Integer score_id;
    private Integer course_id;
    private Integer homework_id;
    private Integer group_id;
    private String grader_id;
    private Integer score;
    private String score_message;

    public Score() {
    }

    public Integer getScore_id() {
        return score_id;
    }

    public void setScore_id(Integer score_id) {
        this.score_id = score_id;
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

    public String getGrader_id() {
        return grader_id;
    }

    public void setGrader_id(String grader_id) {
        this.grader_id = grader_id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScore_message() {
        return score_message;
    }

    public void setScore_message(String score_message) {
        this.score_message = score_message;
    }
}
