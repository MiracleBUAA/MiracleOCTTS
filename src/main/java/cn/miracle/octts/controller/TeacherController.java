package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Course;
import cn.miracle.octts.service.CourseService;
import cn.miracle.octts.service.TeacherService;
import cn.miracle.octts.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Tony on 2017/6/27.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "/course_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInfomation(@RequestParam(value = "course_id", required = true) Integer course_id) {
        BaseResponse response = new BaseResponse();
        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
        } else {
            HashMap<String, Object> data = courseService.Dump2Data(course);
            response = setCorrectResponse(data);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/course_information", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setCourseInfomation(@RequestParam(value = "course_id", required = true) Integer course_id,
                                                            @RequestParam(value = "course_name") String course_name,
                                                            @RequestParam(value = "course_start_time") Date course_start_time,
                                                            @RequestParam(value = "course_end_time") Date course_end_time,
                                                            @RequestParam(value = "course_hours") Integer course_hours,
                                                            @RequestParam(value = "course_location") String course_location,
                                                            @RequestParam(value = "credit") Integer credit,
                                                            @RequestParam(value = "team_limit_information") String team_limit_information,
                                                            @RequestParam(value = "teacher_information") String teacher_information,
                                                            @RequestParam(value = "course_information") String course_information) {
        BaseResponse response = new BaseResponse();
        if (courseService.findCourseById(course_id) == null) {
            response = setParamError();
        } else {
            Course course = new Course();
            course.setCourse_id(course_id);
            course.setCourse_name(course_name);
            course.setCourse_start_time(course_start_time);
            course.setCourse_end_time(course_end_time);
            course.setCourse_hour(course_hours);
            course.setCourse_location(course_location);
            course.setCredit(credit);
            course.setTeam_limit_information(team_limit_information);
            course.setTeacher_information(teacher_information);
            course.setCourse_information(course_information);

            courseService.updateCourse(course);

            response = setCorrectUpdate();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/student_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> student_list(@RequestParam(value = "file") MultipartFile student_list) {

        BaseResponse response = new BaseResponse();
//        try {
//            String student_list_path = FileUtils.saveSingleUploadFile(student_list); // 上传文件
//
//            int studentcount = teacherService.importStudentList(student_list_path); // 写入数据库
//
//            HashMap<String, Object> data = new HashMap<>();
//            data.put("desc", "success");
//            response = setCorrectResponse(data);
//        } catch (IOException e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student_list", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> student_list(@RequestParam(value = "file") MultipartFile student_list,
                                                     @RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        if (course_id == null) {
            response = setParamError();
        } else if (student_list.isEmpty()) {
            response = setFileUploadError();
        } else {
            try {
                String student_list_path = FileUtils.saveSingleUploadFile(student_list); // 上传文件

                int studentcount = teacherService.importStudentList(student_list_path); // 写入数据库

                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "success");
                response = setCorrectResponse(data);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
