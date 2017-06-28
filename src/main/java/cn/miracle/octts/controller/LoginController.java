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

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Tony on 2017/6/27.
 */
@RestController
public class LoginController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private HttpSession httpSession;

    @RequestMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestParam(value = "uid", required = true) Integer uid,
                                              @RequestParam(value = "password", required = true) String password) {

        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        HashMap<String, Object> data = new HashMap<>();
        response.setData(data);

        if (uid == null || password == null) {
            response.setErrorNo(1);
            response.setErrorMsg("参数错误");
        } else {
            Student student = studentService.findStudentById(uid);
            if (student != null) {
                if (password.equals(student.getPassword())) {

                    data.put("uid", student.getStudent_id());
                    data.put("desc", "success");

                    httpSession.setAttribute(student.getStudent_id(), "login");
                    data.put("login_status", httpSession.getAttribute(student.getStudent_id()));

                    response.setData(data);
                } else {
                    response.setErrorNo(3);
                    response.setErrorMsg("密码错误");
                }
            } else {
                response.setErrorNo(2);
                response.setErrorMsg("uid未授权");
            }
        }
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
