package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Course;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.service.CourseService;
import cn.miracle.octts.service.StudentService;
import cn.miracle.octts.service.TeacherService;
import cn.miracle.octts.util.CodeConvert;
import cn.miracle.octts.util.DateConvert;
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
import java.text.ParseException;
import java.util.*;

/**
 * Created by Tony on 2017/7/1.
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    /**
     * API2: 添加新学期
     *
     * @param uid
     * @param course_year
     * @param course_name
     * @param course_start_time
     * @param course_hour
     * @param course_location
     * @param course_credit
     * @param teacher_information
     * @return
     */
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
        Integer cid = courseService.findMaxCourseId();
        course.setCourse_id(cid);

        courseService.endAllCourse();
        try {
            Date course_start_date = DateConvert.string2Date(course_start_time);

            course.setCourse_year(course_year);
            course.setCourse_name(CodeConvert.unicode2String(course_name));
            course.setCourse_start_time(course_start_date);
            course.setCourse_hour(course_hour);
            course.setCourse_location(CodeConvert.unicode2String(course_location));
            course.setCourse_credit(course_credit);
            course.setTeacher_information(CodeConvert.unicode2String(teacher_information));
            course.setCourse_status(new Integer(1));

            courseService.insertCourse(course, uid);
            response = setCorrectInsert();
            HashMap<String, Object> data = new HashMap<String, Object>();
            data.put("course_id", course.getCourse_id());
            response.setData(data);

        } catch (ParseException parseExceptionse) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API3: 修改课程
     *
     * @param uid
     * @param course_id
     * @param course_year
     * @param course_name
     * @param course_start_time
     * @param course_hour
     * @param course_location
     * @param course_credit
     * @param teacher_information
     * @return
     */
    @RequestMapping(value = "/course_update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateCourseInfomation(@RequestParam(value = "uid") String uid,
                                                               @RequestParam(value = "course_id") Integer course_id,
                                                               @RequestParam(value = "course_year", required = false) Integer course_year,
                                                               @RequestParam(value = "course_name", required = false) String course_name,
                                                               @RequestParam(value = "course_start_time", required = false) String course_start_time,
                                                               @RequestParam(value = "course_hour", required = false) Integer course_hour,
                                                               @RequestParam(value = "course_location", required = false) String course_location,
                                                               @RequestParam(value = "course_credit", required = false) Double course_credit,
                                                               @RequestParam(value = "teacher_information", required = false) String teacher_information) {
        BaseResponse response = new BaseResponse();

        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {//修改课程信息
            try {
                if (course_year != null) {
                    course.setCourse_year(course_year);
                }
                if (course_name != null) {
                    course.setCourse_name(CodeConvert.unicode2String(course_name));
                }
                if (course_start_time != null) {
                    Date course_start_date = DateConvert.string2Date(course_start_time);
                    course.setCourse_start_time(course_start_date);
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
                if (teacher_information != null) {
                    course.setTeacher_information(CodeConvert.unicode2String(teacher_information));
                }

                courseService.updateCourse(course, uid);
                response = setCorrectUpdate();
            } catch (ParseException parseExceptionse) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }

    /**
     * API4: 结束课程
     *
     * @param uid
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/course_end", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> endCourse(@RequestParam(value = "uid") String uid,
                                                  @RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else if (course.getCourse_status().intValue() == 0) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        } else {
            course.setCourse_status(0);
            courseService.updateCourse(course, uid);
            response = setCorrectUpdate();
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API5: 显示课程信息
     *
     * @return
     */
    @RequestMapping(value = "/course_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInformation() {
        BaseResponse response = new BaseResponse();
        List<HashMap<String, Object>> course_list = new ArrayList<HashMap<String, Object>>();

        List<Course> course_result = courseService.findAllCourse();
        Iterator<Course> course_iter = course_result.iterator();
        try {
            while (course_iter.hasNext()) {

                HashMap<String, Object> course = courseService.adminCourse2Json(course_iter.next());
                course_list.add(course);


            }
        } catch (ParseException parseException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("course_list", course_list);
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API6: 上传学生名单
     *
     * @return
     */
    @RequestMapping(value = "/student_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getStudentList() {
        BaseResponse response = new BaseResponse();
        List<HashMap<String, Object>> student_list = new ArrayList<HashMap<String, Object>>();

        List<Student> student_result = studentService.findAllStudent();
        Iterator<Student> student_iter = student_result.iterator();
        while (student_iter.hasNext()) {
            HashMap<String, Object> student = studentService.adminStudent2Json(student_iter.next());
            student_list.add(student);
        }

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("student_list", student_list);
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API7: 上传学生名单
     *
     * @param uid
     * @param course_id
     * @param student_list
     * @return
     */
    @RequestMapping(value = "/student_list", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> uploadStudentList(@RequestParam(value = "uid", required = false) String uid,
                                                          @RequestParam(value = "course_id") Integer course_id,
                                                          @RequestParam(value = "file") MultipartFile student_list) {
        BaseResponse response = new BaseResponse();
        if (student_list.isEmpty()) {
            response = setFileUploadError();
        } else {
            try {
                String student_list_path = FileUtils.saveSingleUploadFile(student_list, FileUtils.STUDENT_LIST_FOLDER); // 上传文件
                if (uid == null)
                    uid = "T000";
                Integer studentcount = teacherService.importStudentList(student_list_path, uid); // 写入数据库

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
