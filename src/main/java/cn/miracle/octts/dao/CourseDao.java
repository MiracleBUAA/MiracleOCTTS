package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Course;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by Tony on 2017/6/27.
 */
public interface CourseDao extends BaseMapper<Course> {

    @Select("SELECT course_id, course_name, course_start_time, course_end_time, course_hour, credit, " +
            "course_location, team_limit_information, teacher_information, course_information " +
            "FROM course " +
            "WHERE course_id = #{course_id}")
    @ResultMap("cn.miracle.octts.dao.CourseDao.CourseDetail")
    Course findById(Integer course_id);

    @Update("UPDATE course " +
            "SET course_name=#{course_name}, " +
            "course_start_time=#{course_start_time}, " +
            "course_end_time=#{course_end_time}, " +
            "course_hour=#{course_hour}, " +
            "course_location=#{course_location}, " +
            "credit=#{credit}, " +
            "team_limit_information=#{team_limit_information}, " +
            "teacher_information=#{teacher_information}, " +
            "course_information=#{course_information} " +
            "WHERE course_id=#{course_id}")
    int updateCourse(Course course);
}
