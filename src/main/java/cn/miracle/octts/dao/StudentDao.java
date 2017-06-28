package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Student;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hf on 2017/6/25.
 */
@Mapper
@Component
public interface StudentDao extends BaseMapper<Student> {

    @Select("SELECT student_id, group_id, name, gender, class, email, telephone " +
            "FROM student " +
            "Where student_id = #{stud_id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "student_id", property = "student_id"),
            @Result(column = "group_id", property = "group_id"),
            @Result(column = "name", property = "name"),
            @Result(column = "gender", property = "gender"),
            @Result(column = "class", property = "student_class"),
            @Result(column = "email", property = "email"),
            @Result(column = "telephone", property = "telephone")
    })
    Student findById(Integer stud_id);

    @Select(("SELECT STUDENT_ID, PASSWORD " +
            "FROM STUDENT " +
            "WHERE STUDENT_ID = #{student_id}"))
    @ResultMap("cn.miracle.octts.dao.StudentDao.StudentLogin")
    Student findByIdforLogin(Integer student_id);

    @Insert("INSERT INTO STUDENT (gmt_create, gmt_modified, student_id, password, name, gender, class)" +
            "VALUES (#{createtime},#{updatetime},#{student_id},#{password},#{name},#{gender},#{student_class})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertStudent(Student student);
}
