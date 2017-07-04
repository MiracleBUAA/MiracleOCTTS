package cn.miracle.octts.controller;


import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.dao.CourseDao;
import cn.miracle.octts.entity.Student;
import cn.miracle.octts.entity.Teacher;
import cn.miracle.octts.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tony on 2017/6/27.
 */
//@CrossOrigin(origins = "http://*")
@RestController
public class LoginController extends BaseController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GroupConfirmService groupConfirmService;
    @Autowired
    private GroupApplyService groupApplyService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private HttpSession httpSession;

    private void setLoginSession(String uid, int urank) {
        String user_Login = uid + "_login";
        httpSession.setAttribute(user_Login, urank);
    }

    @RequestMapping("/login")
    public ResponseEntity<BaseResponse> login(@RequestParam(value = "uid", required = true) String uid,
                                              @RequestParam(value = "password", required = true) String password,
                                              @RequestParam(value = "urank", required = true) Integer urank) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        if (uid == null || password == null || urank == null) {
            response = setParamError();
        } else {
            switch (urank) {
                case 1:  // 学生
//                    Integer student_id = Integer.parseInt(uid);
                    Student student = studentService.findStudentByIdForLogin(uid);
                    if (student != null) {
                        if (!password.equals(student.getPassword())) { // 密码错误
                            response.setErrorNo(3);
                            response.setErrorMsg("密码错误");
                            response.setData(data);
                        } else {
                            setLoginSession(uid, urank);
                            data.put("desc", "success");
                            data.put("uid", student.getStudent_id());
                            // TODO: 判断学生是否是团队负责人
                            Integer student_role = 1;
                            ArrayList<String> groupOwners = new ArrayList<>();
                            groupOwners.addAll(groupConfirmService.findGroupOwner());
                            for (String group_owner_id : groupOwners) {
                                if (uid.equals(group_owner_id)) {
                                    student_role = 2;
                                    break;
                                }
                            }
                            if (student_role == 1) {
                                ArrayList<String> groupApplyOwners = new ArrayList<>();
                                groupApplyOwners.addAll(groupApplyService.findGroupApplyOwner());
                                for (String group_apply_owner : groupApplyOwners) {
                                    if (uid.equals(group_apply_owner)) {
                                        student_role = 2;
                                        break;
                                    }
                                }
                            }
                            data.put("urank", student_role);
                            data.put("course_id", courseService.findCurrentCourse());
                            response = setCorrectResponse(data);
                        }
                    } else { // 未授权
                        response.setErrorNo(2);
                        response.setErrorMsg("uid未授权");
                        response.setData(data);
                    }
                    break;
                case 3: // 教师
                    Teacher teacher = teacherService.findTeacherByIdForLogin(uid);
                    if (teacher == null) {
                        response.setErrorNo(2);
                        response.setErrorMsg("uid未授权");
                        response.setData(data);
                    } else {
                        if (!password.equals(teacher.getPassword())) {
                            response.setErrorNo(3);
                            response.setErrorMsg("密码错误");
                            response.setData(data);
                        } else {
                            setLoginSession(uid, urank);
                            data.put("desc", "success");
                            data.put("uid", teacher.getTeacher_id());
                            data.put("urank", urank);
                            data.put("course_id", courseService.findCurrentCourse());
                            response = setCorrectResponse(data);
                        }
                    }
                    break;
                case 4: // 教务
                    Teacher teacherAdmin = teacherService.findTeacherByIdForLogin(uid);
                    if (teacherAdmin == null) {
                        response.setErrorNo(2);
                        response.setErrorMsg("uid未授权");
                        response.setData(data);
                    } else {
                        if (!password.equals(teacherAdmin.getPassword())) {
                            response.setErrorNo(3);
                            response.setErrorMsg("密码错误");
                            response.setData(data);
                        } else {
                            setLoginSession(uid, urank);
                            data.put("desc", "success");
                            data.put("uid", teacherAdmin.getTeacher_id());
                            data.put("urank", urank);
                            data.put("course_id", courseService.findCurrentCourse());
                            response = setCorrectResponse(data);
                        }
                    }
                    break;
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
