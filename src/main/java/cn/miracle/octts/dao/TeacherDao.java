package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * Created by hf on 2017/6/27.
 */
@MapperScan
@Component
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
    Teacher findById(String teacher_id);

    @Select("SELECT TEACHER_ID, PASSWORD " +
            "FROM TEACHER " +
            "WHERE TEACHER_ID = #{teacher_id}")
    @ResultMap("cn.miracle.octts.dao.TeacherDao.TeacherLogin")
    Teacher findByIdForLogin(String teacher_id);

    @Select("SELECT teacher_name FROM teacher WHERE teacher_id = #{teacher_id}")
    String findTeacherNameById(String teacher_id);
}
