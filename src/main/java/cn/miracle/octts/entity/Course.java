package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
//import java.util.Date;
import java.sql.Date;
import java.math.BigDecimal;

/**
 * Created by Tony on 2017/6/27.
 */
public class Course {
    private String course_id;
    private Date course_start_time;
    private Date course_end_time;
    private Integer course_hours;
    private BigDecimal credit;
    private String course_location;



}
