package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Homework;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hf on 2017/6/28.
 */
public interface HomeworkDao extends BaseMapper<Homework> {
    @Select("SELECT homework_id, course_id, teacher_id, title, message, start_time, end_time, resubmit_limit, resubmit_limit_time " +
            "FROM homework " +
            "WHere homework_id = #{homework_id}")
    Homework findById(Integer homework_id);
}
