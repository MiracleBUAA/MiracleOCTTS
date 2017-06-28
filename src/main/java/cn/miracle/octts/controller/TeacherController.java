package cn.miracle.octts.controller;


import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
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
import java.util.HashMap;


/**
 * Created by Tony on 2017/6/27.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/course_information")
    public ResponseEntity<BaseResponse> course_information(@RequestParam(value = "course_id") String course_id) {
        BaseResponse response = setParamError();
        return new ResponseEntity<BaseResponse>(response, HttpStatus.ACCEPTED);


    }

    @RequestMapping(value = "/student_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> student_list() {
        BaseResponse response = new BaseResponse();
        teacherService.importStudentList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/student_list", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> student_list(@RequestParam(value = "file") MultipartFile uploadFile,
                                                     @RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        if (course_id == null) {
            response = setParamError();
        } else if (uploadFile.isEmpty()) {
            response = setFileUploadError();
        } else {
            try {
                FileUtils.saveUploadFiles(Collections.singletonList(uploadFile));

                // TODO: WRITE DATABASE
                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "OK");
                response = setCorrectResponse(data);

            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
