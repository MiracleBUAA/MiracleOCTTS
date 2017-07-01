package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Course;
import cn.miracle.octts.entity.Homework;
import cn.miracle.octts.entity.HomeworkUpload;
import cn.miracle.octts.entity.Resource;
import cn.miracle.octts.service.*;
import cn.miracle.octts.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;


@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HomeworkUploadService homeworkUploadService;

    @RequestMapping(value = "/course_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInformation(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        if (course_id == null) {
            response = setParamError();
        } else {
            Course course = courseService.findCourseById(course_id);
            if (course != null) {
                HashMap<String, Object> data = courseService.teacherCourse2Json(course);
                response = setCorrectResponse(data);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/announcement", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getAnnouncement(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getResource(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();



        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/resource_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadResource(@RequestParam(value = "resource_id") Integer resource_id) {
        BaseResponse response = new BaseResponse();

        Resource resource_download = resourceService.findByIdForDownload(resource_id);
        if (resource_download == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            String resource_url = resource_download.getResource_url();
            String resource_title = resource_download.getResource_title();
            try {
                ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(resource_url);
                org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

                HttpHeaders headers = getFileDownloadHeaders(resource_title);

                return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);

            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
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
    public ResponseEntity<BaseResponse> UploadHomework(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "file") MultipartFile uploadFile,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "homework_id") Integer homework_id,
                                                       @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();
        if (course_id == null || homework_id == null || group_id == null) {
            response = setParamError();
        } else if (uploadFile.isEmpty()) {
            response = setFileUploadError();
        } else {
            // 完成文件上传
            try {
                // 将文件写入服务器
                String filePath = FileUtils.saveSingleUploadFile(uploadFile);
                // TODO: WRITE DATABASE
                //Uid included
                HomeworkUpload homeworkUpload = new HomeworkUpload();
                homeworkUpload.setHomework_upload_id(233);
                homeworkUpload.setCourse_id(course_id);
                homeworkUpload.setHomework_id(homework_id);
                homeworkUpload.setGroup_id(group_id);
                homeworkUpload.setUid(uid);
                homeworkUpload.setHomework_url(filePath);

                homeworkUploadService.InsertHomeworkUpload(homeworkUpload);

                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "OK");
                response = setCorrectResponse(data);

            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/download/", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> DownloadHomework(@RequestParam(value = "course_id") Integer course_id,
                                                                                 @RequestParam(value = "group_id") String group_id,
                                                                                 @RequestParam(value = "homework_id") String homework_id) {
        BaseResponse response = new BaseResponse();

        String file_folder = "test";

        String file_name = "test.txt";

        String downloadFileURL = "/Users/hf/tmp/download/" + file_folder + "/" + file_name;

        try {
            ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(downloadFileURL);
            org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

            HttpHeaders headers = getFileDownloadHeaders(file_name);

            return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/x-msdownload")).body(resource);

        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> CreateGroup(@RequestParam(value = "uid") String uid,
                                                    @RequestParam(value = "student_id") Integer student_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/invitation", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendInvitation(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "student_id") Integer student_id,
                                                       @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> VerifyInvitation(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "student_id") Integer student_id,
                                                         @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/application", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendApplication(@RequestParam(value = "uid") String uid,
                                                        @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/dissolution", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> SendDissolution(@RequestParam(value = "uid") String uid,
                                                        @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "student_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setSutdentScore(@RequestParam(value = "uid") String uid,
                                                        @RequestParam(value = "student_id") Integer student_id,
                                                        @RequestParam(value = "homework_id") Integer homework_id,
                                                        @RequestParam(value = "iteration_id") Integer iteration_id,
                                                        @RequestParam(value = "score") Integer score) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "group_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setGroupScore(@RequestParam(value = "uid") String uid,
                                                      @RequestParam(value = "group_id") Integer group_id,
                                                      @RequestParam(value = "homework_id") Integer homework_id,
                                                      @RequestParam(value = "iteration_id") Integer iteration_id,
                                                      @RequestParam(value = "score") Integer score) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
