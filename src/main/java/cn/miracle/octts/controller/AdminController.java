package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Course;
import cn.miracle.octts.service.CourseService;
import cn.miracle.octts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Tony on 2017/7/1.
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/new_course", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> initCourseInfomation(@RequestParam(value = "uid") String uid,
                                                             @RequestParam(value = "course_year") Integer course_year,
                                                             @RequestParam(value = "course_name") String course_name,
                                                             @RequestParam(value = "course_start_time") String course_start_time,
                                                             @RequestParam(value = "course_hour") Integer course_hour,
                                                             @RequestParam(value = "course_location") String course_location,
                                                             @RequestParam(value = "course_credit") Double course_credit,
                                                             @RequestParam(value = "teacher_information") String teacher_information) {
        BaseResponse response = new BaseResponse();
        Course course = new Course();
        Integer cid = new Integer(1);
        while (courseService.findCourseById(cid) != null) { //查找唯一course_id
            cid++;
        }
        course.setCourse_id(cid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date course_start_date = sdf.parse(course_start_time);

            course.setCourse_year(course_year);
            course.setCourse_name(course_name);
            course.setCourse_start_time(course_start_date);
            course.setCourse_hour(course_hour);
            course.setCourse_location(course_location);
            course.setCourse_credit(course_credit);
            course.setTeacher_information(teacher_information);
            course.setCourse_status(new Integer(1));

            courseService.insertCourse(course, uid);
            response = setCorrectInsert();

        } catch (ParseException parseExceptionse) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
