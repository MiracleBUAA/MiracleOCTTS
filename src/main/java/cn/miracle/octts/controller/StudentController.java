package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.Announcement;
import cn.miracle.octts.entity.Course;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HomeworkUploadService homeworkUploadService;

    /**
     * API34: 查看课程信息
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/course_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInformation(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            try {
                HashMap<String, Object> data = courseService.course2Json(course);
                response = setCorrectResponse(data);
            } catch (ParseException parseExceptionse) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API48: 查看通知列表
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/announcement_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getAnnouncement(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        List<HashMap<String, Object>> announcement_list = new ArrayList<HashMap<String, Object>>();

        List<Announcement> announcement_result = announcementService.findAnnouncementByCourseId(course_id);
        Iterator<Announcement> announcement_iter = announcement_result.iterator();
        try {
            while (announcement_iter.hasNext()) {
                HashMap<String, Object> announcement = announcementService.announcement2Json(announcement_iter.next());
                announcement_list.add(announcement);
            }
        } catch (ParseException parseException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("announcement_list", announcement_list);

        response = setCorrectResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getResource(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/resource_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadResource(
            @RequestParam(value = "resource_id") Integer resource_id) {

        BaseResponse response = new BaseResponse();

        Resource resource_download = resourceService.findByIdForDownload(resource_id);
        if (resource_download == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
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
                String filePath = FileUtils.saveSingleUploadFile(uploadFile, FileUtils.HOMEWORK_UPLOAD_FOLDER);
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

}
