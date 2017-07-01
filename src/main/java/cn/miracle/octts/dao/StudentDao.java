package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hf on 2017/6/25.
 */
@Mapper
@Component
public interface StudentDao extends BaseMapper<Student> {

    @Select("SELECT student_id, group_id, student_name, student_gender, student_class, " +
            "student_absent, student_rate, personal_score, group_score " +
            "FROM student ")
    @ResultMap("cn.miracle.octts.dao.StudentDao.StudentDetail")
    List<Student> findAllStudent();

    @Select("SELECT student_id, group_id, student_name, student_gender, student_class, " +
            "student_absent, student_rate, personal_score, group_score " +
            "FROM student " +
            "Where student_id = #{stud_id}")
    @ResultMap("cn.miracle.octts.dao.StudentDao.StudentDetail")
    Student findStudentById(Integer student_id);

    @Select(("SELECT STUDENT_ID, PASSWORD " +
            "FROM STUDENT " +
            "WHERE STUDENT_ID = #{student_id}"))
    @ResultMap("cn.miracle.octts.dao.StudentDao.StudentLogin")
    Student findByIdforLogin(String student_id);

    @Insert("INSERT INTO STUDENT (gmt_create, gmt_modified, uid, student_id, password, student_name, student_gender, student_class)" +
            "VALUES (#{createtime},#{updatetime},#{uid},#{student_id},#{password},#{student_name},#{student_gender},#{student_class})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertStudent(Student student);

}
