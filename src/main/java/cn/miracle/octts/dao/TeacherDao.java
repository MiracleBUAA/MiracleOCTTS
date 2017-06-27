package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Teacher;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * Created by hf on 2017/6/27.
 */
public interface TeacherDao extends BaseMapper<Teacher> {
    @Select("SELECT TEACHER_ID, PASSWORD, NAME, EMAIL, TELEPHONE " +
            "FROM TEACHER " +
            "WHERE TEACHER_ID = #{teacher_id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "teacher_id", property = "teacher_id"),
            @Result(column = "password", property = "password"),
            @Result(column = "name", property = "name"),
            @Result(column = "email", property = "email"),
            @Result(column = "telephone", property = "telephone")
    })
    Teacher findById(Integer teacher_id);
}
