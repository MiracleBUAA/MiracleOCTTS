package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.service.StudentService;
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


@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/course_infomation", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInfomation(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        if (course_id == null) {
            response = setParamError();
        } else {
            HashMap<String, Object> data = new HashMap<>();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/announcement", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getAnnouncement(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getResource(@RequestParam(value = "course_id") Integer course_id,
                                                    @RequestParam(value = "resource_id") Integer resource_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/homework", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomework(@RequestParam(value = "course_id") Integer course_id,
                                                    @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * 上传作业文件
     */
    @RequestMapping(value = "/homework_upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> UploadHomework(@RequestParam(value = "file") MultipartFile uploadFile,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "homework_id") Integer homework_id,
                                                       @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();
        if (course_id == null || homework_id == null || group_id == null) {
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

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> DownloadHomework(@RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "homework_id") Integer homework_id,
                                                         @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> CreateGroup(@RequestParam(value = "student_id") Integer student_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/invitation", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendInvitation(@RequestParam(value = "student_id") Integer student_id,
                                                       @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> VerifyInvitation(@RequestParam(value = "student_id") Integer student_id,
                                                         @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendApplication(@RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/dissolution", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendDissolution(@RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "student_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setSutdentScore(@RequestParam(value = "student_id") Integer student_id,
                                                        @RequestParam(value = "homework_id") Integer homework_id,
                                                        @RequestParam(value = "iteration_id") Integer iteration_id,
                                                        @RequestParam(value = "score") Integer score) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "group_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setGroupScore(@RequestParam(value = "group_id") Integer group_id,
                                                      @RequestParam(value = "homework_id") Integer homework_id,
                                                      @RequestParam(value = "iteration_id") Integer iteration_id,
                                                      @RequestParam(value = "score") Integer score) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
