package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Course;
import cn.miracle.octts.service.CourseService;
import cn.miracle.octts.service.TeacherService;
import cn.miracle.octts.util.FileUtils;
import cn.miracle.octts.util.CodeConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ResponseEntity<BaseResponse> getCourseInfomation(@RequestParam(value = "course_id") Integer course_id) {
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
    public ResponseEntity<BaseResponse> setCourseInfomation(@RequestParam(value = "uid") String uid,
                                                            @RequestParam(value = "course_id") Integer course_id,
                                                            @RequestParam(value = "course_name", required = false) String course_name,
                                                            @RequestParam(value = "course_start_time", required = false) String course_start_time,
                                                            @RequestParam(value = "course_hour", required = false) Integer course_hour,
                                                            @RequestParam(value = "course_location", required = false) String course_location,
                                                            @RequestParam(value = "course_credit", required = false) Double course_credit,
                                                            @RequestParam(value = "team_limit_information", required = false) String team_limit_information,
                                                            @RequestParam(value = "teacher_information", required = false) String teacher_information,
                                                            @RequestParam(value = "course_information", required = false) String course_information) {
        BaseResponse response = new BaseResponse();

        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            //修改课程信息
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (course_start_time != null) {
                    Date course_start_date = sdf.parse(course_start_time);
                    course.setCourse_start_time(course_start_date);
                }

                if (course_name != null) {
                    course.setCourse_name(CodeConvert.unicode2String(course_name));
                }
                if (course_hour != null) {
                    course.setCourse_hour(course_hour);
                }
                if (course_location != null) {
                    course.setCourse_location(CodeConvert.unicode2String(course_location));
                }
                if (course_credit != null) {
                    course.setCourse_credit(course_credit);
                }
                if (team_limit_information != null) {
                    course.setTeam_limit_information(CodeConvert.unicode2String(team_limit_information));
                }
                if (teacher_information != null) {
                    course.setTeacher_information(CodeConvert.unicode2String(teacher_information));
                }
                if (course_information != null) {
                    course.setCourse_information(CodeConvert.unicode2String(course_information));
                }
                courseService.updateCourse(course, uid);
                response = setCorrectUpdate();
            } catch (ParseException parseExceptionse) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }




    @RequestMapping(value = "/student_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> student_list() {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student_list", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> student_list(@RequestParam(value = "uid") String uid,
                                                     @RequestParam(value = "file") MultipartFile student_list) {
        BaseResponse response = new BaseResponse();
        if (student_list.isEmpty()) {
            response = setFileUploadError();
        } else {
            try {
                String student_list_path = FileUtils.saveSingleUploadFile(student_list); // 上传文件
                if (uid == null)
                    uid = "T001";
                int studentcount = teacherService.importStudentList(student_list_path, uid); // 写入数据库

                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "success");
                response = setCorrectResponse(data);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/homework", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomework(@RequestParam(value = "course_id", required = true) Integer course_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/homework", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setHomework(@RequestParam(value = "course_id", required = true) Integer course_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/new_homework", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> initHomework(@RequestParam(value = "course_id", required = true) Integer course_id) {
        BaseResponse response = new BaseResponse();
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
