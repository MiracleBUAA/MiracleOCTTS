package cn.miracle.octts.controller;


import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.entity.Teacher;
import cn.miracle.octts.service.StudentService;
import cn.miracle.octts.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Tony on 2017/6/27.
 */
@RestController
public class LoginController extends BaseController{

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private HttpSession httpSession;

    private void setLoginSession(String uid, int urank) {
        String user_Login = uid + "_login";
        httpSession.setAttribute(user_Login, urank);
    }

    @RequestMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestParam(value = "uid", required = true) String uid,
                                              @RequestParam(value = "password", required = true) String password,
                                              @RequestParam(value = "urank") Integer urank) {

        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        if (uid == null || password == null || urank == null) {
            response = setParamError();
        } else {
            switch (urank) {
                case 1:  // 学生
                    Integer student_id = Integer.parseInt(uid);
                    Student student = studentService.findStudentByIdForLogin(student_id);
                    if (student == null) {  // 未授权
                        response.setErrorNo(2);
                        response.setErrorMsg("uid未授权");
                        response.setData(data);
                    }
                    else {
                        if(password.equals(student.getPassword())) { // 密码错误
                            response.setErrorNo(3);
                            response.setErrorMsg("密码错误");
                            response.setData(data);
                        }
                        else {
                            setLoginSession(uid, urank);
                            data.put("desc", "success");
                            response = setCorrectResponse(data);
                        }
                    }
                case 2: // 教师
                    Teacher teacher = teacherService.findTeacherByIdForLogin(uid);
                    if (teacher == null) {
                        response.setErrorNo(2);
                        response.setErrorMsg("uid未授权");
                        response.setData(data);
                    }
                    else {
                        if (password.equals(teacher.getPassword())) {
                            response.setErrorNo(3);
                            response.setErrorMsg("密码错误");
                            response.setData(data);
                        }
                        else {
                            setLoginSession(uid, urank);
                            data.put("desc", "success");
                            response = setCorrectResponse(data);
                        }
                    }
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> Logout(@RequestParam(value = "uid", required = true) String uid,
                                               @RequestParam(value = "urank") Integer urank) {
        BaseResponse response = new BaseResponse();



        return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
    }
}
