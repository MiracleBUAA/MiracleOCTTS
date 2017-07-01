package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Course;
import org.apache.ibatis.annotations.*;

/**
 * Created by Tony on 2017/6/27.
 */
public interface CourseDao extends BaseMapper<Course> {

    @Select("SELECT uid, course_id, course_year, course_start_time, course_status, course_name, course_hour, " +
            "course_credit, course_location, team_limit_information, teacher_information, course_information " +
            "FROM course " +
            "WHERE course_id = #{course_id}")
    @ResultMap("cn.miracle.octts.dao.CourseDao.CourseDetail")
    Course findCourseById(Integer course_id);

    @Update("UPDATE course " +
            "SET gmt_modified = #{updatetime}, " +
            "course_year = #{course_year}, " +
            "course_name = #{course_name}, " +
            "course_start_time = #{course_start_time}, " +
            "course_hour = #{course_hour}, " +
            "course_location = #{course_location}, " +
            "course_credit = #{course_credit}, " +
            "teacher_information = #{teacher_information}" +
            "WHERE course_id = #{course_id}")
    int updateCourse(Course course);

    @Insert("INSERT INTO course(gmt_create, gmt_modified, uid, course_id, course_year, course_start_time, " +
            "course_status, course_name, course_hour, course_credit, course_location, team_limit_information, " +
            "teacher_information, course_information)" +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{course_id}, #{course_year}, #{course_start_time}, " +
            "#{course_status}, #{course_name}, #{course_hour}, #{course_credit}, #{course_location}, " +
            "#{team_limit_information}, #{teacher_information}, #{course_information})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertCourse(Course course);
}
