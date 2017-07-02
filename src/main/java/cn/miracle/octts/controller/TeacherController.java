package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.*;
import cn.miracle.octts.service.*;
import cn.miracle.octts.util.CodeConvert;
import cn.miracle.octts.util.DateConvert;
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
import java.util.*;

/**
 * Created by Tony on 2017/6/27.
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private HomeworkUploadService homeworkUploadService;

    @Autowired
    private GroupConfirmService groupConfirmService;

    /**
     * API7: 课程信息
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/course_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getCourseInfomation(@RequestParam(value = "course_id") Integer course_id) {
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
     * API7: 课程信息
     *
     * @param uid
     * @param course_id
     * @param team_limit_information
     * @param course_information
     * @return
     */
    @RequestMapping(value = "/course_information", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setCourseInfomation(@RequestParam(value = "uid") String uid,
                                                            @RequestParam(value = "course_id") Integer course_id,
                                                            @RequestParam(value = "team_limit_information", required = false) String team_limit_information,
                                                            @RequestParam(value = "teacher_information", required = false) String teacher_information,
                                                            @RequestParam(value = "course_information", required = false) String course_information) {
        BaseResponse response = new BaseResponse();

        Course course = courseService.findCourseById(course_id);
        if (course == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        } else {
            if (team_limit_information != null) {
                course.setTeam_limit_information(CodeConvert.unicode2String(team_limit_information));
            }
            if (teacher_information != null) {
                course.setTeacher_information(CodeConvert.unicode2String(teacher_information));
            }
            if (course_information != null) {
                course.setCourse_information(CodeConvert.unicode2String(course_information));
            }

            courseService.updateCourse(course, uid);
            response = setCorrectUpdate();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    /**
     * API8: 查看学生名单
     *
     * @return
     */
    @RequestMapping(value = "/student_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> student_list() {
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


    @RequestMapping(value = "/homework", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomework(@RequestParam(value = "course_id", required = true) Integer course_id) {
        BaseResponse response = new BaseResponse();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API.13:教师创建新作业
     */
    @RequestMapping(value = "/new_homework", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> newHomework(@RequestParam(value = "uid") String uid,
                                                    @RequestParam(value = "course_id") Integer course_id,
                                                    @RequestParam(value = "homework_title") String homework_title,
                                                    @RequestParam(value = "homework_start_time") String homework_start_time,
                                                    @RequestParam(value = "homework_end_time") String homework_end_time,
                                                    @RequestParam(value = "homework_score") Integer homework_score,
                                                    @RequestParam(value = "homework_message") String homework_message,
                                                    @RequestParam(value = "resubmit_limit") Integer resubmit_limit) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();
        Homework new_homework = new Homework();

        Integer homework_id = homeworkService.findMaxHomeworkId() + 1;
        new_homework.setHomework_id(homework_id);

        new_homework.setUid(uid);
        new_homework.setTeacher_id(uid);

        new_homework.setCourse_id(course_id);
        homework_title = CodeConvert.unicode2String(homework_title);
        new_homework.setHomework_title(homework_title);
        Date start_time = null;
        Date end_time = null;
        try {
            homework_start_time = CodeConvert.unicode2String(homework_start_time);
            homework_end_time = CodeConvert.unicode2String(homework_end_time);
            start_time = DateConvert.string2Datetime(homework_start_time);
            end_time = DateConvert.string2Datetime(homework_end_time);
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(null);
        }
        new_homework.setHomework_start_time(start_time);
        new_homework.setHomework_end_time(end_time);
        new_homework.setHomework_score(homework_score);
        new_homework.setResubmit_limit(resubmit_limit);
        homework_message = CodeConvert.unicode2String(homework_message);
        new_homework.setHomework_message(homework_message);

        try {
            homeworkService.InsertHomework(new_homework);
            data.put("desc", "OK");
            response = setCorrectResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * API.14:教师——查看作业列表
     **/
    @RequestMapping(value = "homework_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomeworkList(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        HashMap<String, Object> data = new HashMap<>();

        try {
            List<HashMap<String, Object>> homework_list = homeworkService.getHomeworkList(course_id);
            data.put("homework_list", homework_list);
        } catch (ParseException parseException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API.15:教师——查看学生作业
     */
    @RequestMapping(value = "homework_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomeworkinformation(@RequestParam(value = "course_id") Integer course_id,
                                                               @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        Homework homework = homeworkService.findHomeworkById(homework_id);
        data.put("homework_id", homework.getHomework_id());
        data.put("course_id", homework.getCourse_id());
        data.put("homework_title", homework.getHomework_title());

        try {
            data.put("homework_start_time", DateConvert.datetime2String(homework.getHomework_start_time()));
            data.put("homework_end_time", DateConvert.datetime2String(homework.getHomework_end_time()));
        } catch (ParseException e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        data.put("homework_score", homework.getHomework_score());
        data.put("homework_message", homework.getHomework_message());
        data.put("resubmit_limit", homework.getResubmit_limit());

        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * API.16:教师更新作业
     */
    @RequestMapping(value = "/homework_update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateHomework(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "homework_id") Integer homework_id,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "homework_title") String homework_title,
                                                       @RequestParam(value = "homework_start_time") String homework_start_time,
                                                       @RequestParam(value = "homework_end_time") String homework_end_time,
                                                       @RequestParam(value = "homework_score") Integer homework_score,
                                                       @RequestParam(value = "homework_message") String homework_message,
                                                       @RequestParam(value = "resubmit_limit") Integer resubmit_limit) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        Homework homework = homeworkService.findHomeworkById(homework_id);
        if (homework == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            homework.setUid(uid);
            homework_title = CodeConvert.unicode2String(homework_title);
            homework_message = CodeConvert.unicode2String(homework_message);
            homework.setHomework_title(homework_title);
            homework.setHomework_message(homework_message);
            homework.setHomework_score(homework_score);
            homework.setResubmit_limit(resubmit_limit);

            try {
                homework_start_time = CodeConvert.unicode2String(homework_start_time);
                homework_end_time = CodeConvert.unicode2String(homework_end_time);
                Date start_time = DateConvert.string2Datetime(homework_start_time);
                Date end_time = DateConvert.string2Datetime(homework_end_time);
                homework.setHomework_start_time(start_time);
                homework.setHomework_end_time(end_time);
            } catch (ParseException e) {
                return ResponseEntity.badRequest().body(null);
            }

            try {

                homeworkService.updateHomework(homework);
                data.put("desc", "OK");
                response = setCorrectResponse(data);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * API.17:教师——删除作业
     */
    @RequestMapping(value = "/homework_delete", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> deleteHomework(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();
        try {
            homeworkService.deleteHomework(homework_id);
            data.put("desc", "OK");
            response = setCorrectResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * API.18:教师——查看学生提交情况
     */
    public ResponseEntity<BaseResponse> getGroupHomeworkUpload(@RequestParam(value = "course_id") Integer course_id,
                                                               @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        Homework homework = homeworkService.findHomeworkById(homework_id);
        if (homework == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            // 写入homework信息部分
            data.put("homework_id", homework.getHomework_id());
            data.put("homework_name", homework.getHomework_title());
            data.put("homework_score", homework.getHomework_score());
            data.put("homework_message", homework.getHomework_message());
            data.put("teacher_name", teacherService.findTeacherNameById(homework.getTeacher_id()));

            try {
                data.put("homework_start_time", DateConvert.datetime2String(homework.getHomework_start_time()));
                data.put("homework_end_time", DateConvert.datetime2String(homework.getHomework_end_time()));
            } catch (ParseException e) {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            // TODO：写入提交作业列表


        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API.19 教师——作业评分
     */
    @RequestMapping(value = "/homework_set_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setHomeworkScore(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "homework_id") Integer homework_id,
                                                         @RequestParam(value = "group_id") Integer group_id,
                                                         @RequestParam(value = "score") Double score,
                                                         @RequestParam(value = "score_message") String score_message) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        // todo: 评分

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @RequestMapping(value = "/homework_group_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadGroupHomeworkUpload(
            @RequestParam(value = "homework_upload_id") Integer homework_upload_id) {
        HomeworkUpload homework_upload = homeworkUploadService.findHomeworkUploadById(homework_upload_id);
        if (homework_upload == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            String homework_url = homework_upload.getHomework_url();
            String file_name = homework_upload.getFile_name();

            try {
                ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(homework_url);

                org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

                HttpHeaders headers = getFileDownloadHeaders(file_name);

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.parseMediaType("application/x-msdownload"))
                        .body(resource);


            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }


    /**
     * API.9:教师获取课程资源列表
     *
     * @param course_id 课程id
     */
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getResource(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();

        HashMap<String, Object> data = new HashMap<>();

        try {
            List<HashMap<String, Object>> resource_list = resourceService.getResourceList(course_id);
            data.put("resource_list", resource_list);

            List<String> resource_types = resourceService.findResourceType(course_id);

            data.put("resource_type_list", resource_types);

            response = setCorrectResponse(data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ParseException pe) {
            return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * API10:教师上传课程资源
     *
     * @param course_id 课程id
     */
    @RequestMapping(value = "/resource_upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> UploadResource(@RequestParam(value = "uid", required = false) String uid,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "resource_type") String resource_type,
                                                       @RequestParam(value = "title") String resource_title,
                                                       @RequestParam(value = "file") MultipartFile resource_file) {
        BaseResponse response = new BaseResponse();
        if (resource_file.isEmpty()) {
            response = setFileUploadError();
        } else {  // 文件存在
            try {
                String resource_url = FileUtils.saveSingleUploadFile(resource_file, FileUtils.RESOURCE_FOLDER);

                Integer resource_id = resourceService.findMaxResource() + 1;

                resource_title = resource_file.getOriginalFilename();

                Resource resource_upload = new Resource();
                resource_upload.setResource_id(resource_id);
                resource_upload.setUid(uid);
                resource_upload.setCourse_id(course_id);
                resource_upload.setResource_type(resource_type);
                resource_upload.setResource_title(resource_title);
                resource_upload.setTeacher_id(uid);
                resource_upload.setResource_url(resource_url);

                resourceService.InsertResource(resource_upload);

                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "OK");
                response = setCorrectResponse(data);

            } catch (IOException e) {
                return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API11:教师下载资源
     */
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

                return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.parseMediaType("application/x-msdownload"))
                        .body(resource);

            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * API12:教师删除资源
     */
    @RequestMapping(value = "/resource_delete", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> deleteResource(@RequestParam(value = "resource_id") Integer resource_id) {
        BaseResponse response = new BaseResponse();

        resourceService.deleteResource(resource_id);

        HashMap<String, Object> data = new HashMap<>();
        data.put("desc", "success");
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API21: 发布通知
     *
     * @param uid
     * @param course_id
     * @param announcement_title
     * @param announcement_message
     * @return
     */
    @RequestMapping(value = "/new_announcement", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> initAnnouncement(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "announcement_title") String announcement_title,
                                                         @RequestParam(value = "announcement_message") String announcement_message) {
        BaseResponse response = new BaseResponse();
        Announcement announcement = new Announcement();

        announcement.setAnnouncement_id(announcementService.findMaxAnnouncementId());
        announcement.setTeacher_id(uid);
        announcement.setCourse_id(course_id);
        announcement.setAnnouncement_title(CodeConvert.unicode2String(announcement_title));
        announcement.setAnnouncement_message(CodeConvert.unicode2String(announcement_message));

        announcementService.insertAnnouncement(announcement, uid);
        setCorrectInsert();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API22: 修改通知
     *
     * @param uid
     * @param course_id
     * @param announcement_id
     * @param announcement_title
     * @param announcement_message
     * @return
     */
    @RequestMapping(value = "/announcement_update", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> updateAnnouncement(@RequestParam(value = "uid") String uid,
                                                           @RequestParam(value = "course_id") Integer course_id,
                                                           @RequestParam(value = "announcement_id") Integer announcement_id,
                                                           @RequestParam(value = "announcement_title") String announcement_title,
                                                           @RequestParam(value = "announcement_message") String announcement_message) {
        BaseResponse response = new BaseResponse();

        Announcement announcement = announcementService.findAnnouncementById(announcement_id);
        if (announcement == null || announcement.getCourse_id() != course_id) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        announcement.setTeacher_id(uid);
        announcement.setAnnouncement_title(CodeConvert.unicode2String(announcement_title));
        announcement.setAnnouncement_message(CodeConvert.unicode2String(announcement_message));

        announcementService.updateAnnouncement(announcement, uid);
        setCorrectInsert();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API23: 通知列表
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/announcement_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getAnnouncement(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        try {
            List<HashMap<String, Object>> announcement_list = announcementService.getAnnouncementList(course_id);
            data.put("announcement_list", announcement_list);
        } catch (ParseException parseException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response = setCorrectResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/group_confirm_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getGroupConfirm(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> group_confirm_list = groupConfirmService.getGroupConfirmList(course_id);

        data.put("group_confirm_list", group_confirm_list);
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
