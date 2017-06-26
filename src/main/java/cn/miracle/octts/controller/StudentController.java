package cn.miracle.octts.controller;

import cn.miracle.octts.entity.Student;
import cn.miracle.octts.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hf on 2017/6/26.
 */
@RestController
public class StudentController {
    private static final String template = "/course_information<br/>/personal_information<br/>/announcement<br/>/course_resources";
    private final AtomicLong counter = new AtomicLong();
    private StudentService studentService;

    @RequestMapping("/student")
    public String student() {
        return template;
    }


    @RequestMapping("/student/personal_information")///student/personal_information?id = 14211133
    public Student personInformation(@RequestParam(value = "uid") Integer uid) {
        if (uid == null) {
            return new Student();
        } else {
            return studentService.findStudentById(uid);
        }
    }
}
