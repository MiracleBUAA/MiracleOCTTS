package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hf on 2017/6/26.
 */
@RestController
public class StudentController {
    private static final String template = "/course_information<br/>/personal_information<br/>/announcement<br/>/course_resources";
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private StudentService studentService;

    @RequestMapping("/student")
    public String student() {
        return template;
    }

    @RequestMapping("/student/announcement")
    public String announcement() {
        return "No announcement!";
    }


    @RequestMapping("/student/personal_information")///student/personal_information?id = 14211133
    public Student personInformation(@RequestParam(value = "uid") Integer uid) {
        if (uid == null) {
            return new Student();
        } else {
            return studentService.findStudentById(uid);
        }
    }

    @RequestMapping("/student/info")
    public ResponseEntity<BaseResponse> studentInfo(@RequestParam(value = "uid")Integer uid) {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        HashMap<String, Object> data = new HashMap<>();
        response.setData(data);
        if(uid == null) {
            response.setErrorNo(1);
            response.setErrorMsg("参数错误");
            response.setData(null);
        }
        else {
            Student student = studentService.findStudentById(uid);
            data.put("name", student.getName());
            data.put("student_id", student.getStudent_id());
            response.setData(data);
        }

        return new ResponseEntity<BaseResponse>(response, HttpStatus.ACCEPTED);
    }
}
