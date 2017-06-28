package cn.miracle.octts.service;


import cn.miracle.octts.dao.CourseDao;
import cn.miracle.octts.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by Tony on 2017/6/27.
 */
@Service
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    private Date currentTime = new Date(System.currentTimeMillis());

    public Course findCourseById(Integer course_id) {
        return courseDao.findById(course_id);
    }

    public HashMap<String, Object> Dump2Data(Course course) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("course_id", course.getCourse_id());
        data.put("course_name", course.getCourse_name());
        data.put("course_start_time", course.getCourse_start_time());
        data.put("course_end_time", course.getCourse_end_time());
        data.put("course_hour", course.getCourse_hour());
        data.put("course_location", course.getCourse_location());
        data.put("credit", course.getCredit());
        data.put("team_limit_information", course.getTeam_limit_information());
        data.put("teacher_information", course.getTeacher_information());
        data.put("course_information", course.getCourse_information());
        return data;
    }

    public int updateCourse(Course course, String uid) {
        course.setUpdatetime(currentTime);
        course.setUid(uid);
        return courseDao.updateCourse(course);
    }

    public int insertCourse(Course course, String uid){
        course.setCreatetime(currentTime);
        course.setUpdatetime(currentTime);
        course.setUid(uid);
        return courseDao.insertCourse(course);
    }
}
