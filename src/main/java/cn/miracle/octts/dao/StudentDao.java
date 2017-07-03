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
    Student findStudentById(String student_id);

    @Select(("SELECT STUDENT_ID, PASSWORD " +
            "FROM STUDENT " +
            "WHERE STUDENT_ID = #{student_id}"))
    @ResultMap("cn.miracle.octts.dao.StudentDao.StudentLogin")
    Student findByIdforLogin(String student_id);

    @Insert("INSERT INTO STUDENT (gmt_create, gmt_modified, uid, student_id, password, student_name, student_gender, student_class)" +
            "VALUES (#{createtime},#{updatetime},#{uid},#{student_id},#{password},#{student_name},#{student_gender},#{student_class})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insertStudent(Student student);

    @Select("SELECT student_name FROM student WHERE student_id = #{student_id}")
    String findStudentNameById(String student_id);

    @Update("UPDATE student SET group_id = #{group_id} WHERE student_id = #{student_id}")
    Integer updateGroupId(Student student);

    @Select("SELECT student_id from student")
    List<String> findAllStudentId();

    @Update("UPDATE student SET group_score=#{group_score} WHERE student_id=#{student_id}")
    void setGroupScoreById(@Param(value = "student_id") String student_id,
                           @Param(value = "group_score") Double group_score);

    @Update("UPDATE student SET personal_score=#{student_id} WHERE student_id=#{personal_score}")
    void setPersonalScoreById(Student student);
}
