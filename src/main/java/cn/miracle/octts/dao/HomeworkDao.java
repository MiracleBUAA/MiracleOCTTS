package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Homework;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hf on 2017/6/28.
 */
public interface HomeworkDao extends BaseMapper<Homework> {
    @Select("SELECT homework_id, course_id, teacher_id, homework_score, homework_status, homework_title, homework_message, homework_start_time, homework_end_time, resubmit_limit " +
            "FROM homework " +
            "WHERE homework_id = #{homework_id}")
    Homework findHomeworkById(Integer homework_id);
}
