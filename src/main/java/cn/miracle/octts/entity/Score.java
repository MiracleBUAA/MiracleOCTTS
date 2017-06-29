package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import java.util.Date;

/**
 * Created by Tony on 2017/6/29.
 */
public class Score extends BaseEntity {
    private Integer score_id;
    private Integer course_id;
    private Integer homework_id;
    private Integer group_id;
    private String grader_id;
    private double score;
    private String message;
    private Date score_time; //DateTime
}
