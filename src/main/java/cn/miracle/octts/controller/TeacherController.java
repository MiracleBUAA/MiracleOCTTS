package cn.miracle.octts.controller;

import cn.miracle.octts.common.base.BaseController;
import cn.miracle.octts.common.base.BaseResponse;
import cn.miracle.octts.entity.*;
import cn.miracle.octts.service.*;
import cn.miracle.octts.util.CodeConvert;
import cn.miracle.octts.util.DateConvert;
import cn.miracle.octts.util.ExportForm;
import cn.miracle.octts.util.FileUtils;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
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
import java.text.ParseException;
import java.util.*;

/**
 * Created by Tony on 2017/6/27.
 */
//@CrossOrigin(origins = "http://*")
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
    private GroupApplyService groupApplyService;

    @Autowired
    private GroupApplyMemberService groupApplyMemberService;

    @Autowired
    private GroupConfirmService groupConfirmService;

    @Autowired
    private GroupConfirmMemberService groupConfirmMemberService;

    @Autowired
    private ScoreSerivce scoreSerivce;

    @Autowired
    private InvitationService invitationService;

    @Autowired
    private ExportForm exportForm;

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
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> student_list = studentService.getStudentList();
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
    @RequestMapping(value = "/homework_group_upload", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getGroupHomeworkUpload(@RequestParam(value = "course_id") Integer course_id,
                                                               @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        Homework homework = homeworkService.findHomeworkById(homework_id);
        if (homework == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            try {
                // 写入homework信息部分
                data.put("homework_id", homework.getHomework_id());
                data.put("homework_name", homework.getHomework_title());
                data.put("homework_score", homework.getHomework_score());
                data.put("homework_message", homework.getHomework_message());
                data.put("teacher_name", teacherService.findTeacherNameById(homework.getTeacher_id()));
                data.put("homework_start_time", DateConvert.datetime2String(homework.getHomework_start_time()));
                data.put("homework_end_time", DateConvert.datetime2String(homework.getHomework_end_time()));
                data.put("homework_resubmit_limit", homework.getResubmit_limit());
            } catch (ParseException e) {
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //修改后的：写入group_list
            ArrayList<GroupConfirm> group_list = new ArrayList<>();
            group_list.addAll(groupConfirmService.findGroupConfirmByCourseId(course_id));
            try {

                List<HashMap<String, Object>> group_homework_list = groupConfirmService.getGroupHomeworkList(group_list, homework_id);

                data.put("group_list", group_homework_list);
                response = setCorrectResponse(data);
                return new ResponseEntity<>(response, HttpStatus.OK);

            } catch (ParseException e) {
                return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * API.19 教师——作业评分
     */
    @RequestMapping(value = "/homework_set_score", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setHomeworkScore(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "homework_id") Integer homework_id,
                                                         @RequestParam(value = "group_id") Integer group_id,
                                                         @RequestParam(value = "score") String score,
                                                         @RequestParam(value = "score_message") String score_message) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();
        Double score_to_set = Double.valueOf(score);
        score_message = CodeConvert.unicode2String(score_message);
        try {
            // 先查找平分是否存在
            Score homework_score = scoreSerivce.findScoreByHomeworkIdAndGroupId(homework_id, group_id);
            if (homework_score == null) {
                // 分数不存在，添加评分
                homework_score = new Score();
                homework_score.setUid(uid);
                homework_score.setCourse_id(course_id);
                homework_score.setHomework_id(homework_id);
                homework_score.setGroup_id(group_id);
                homework_score.setScore(score_to_set);
                homework_score.setGrader_id(uid);
                homework_score.setScore_message(score_message);
                scoreSerivce.insertScore(homework_score);
            } else {
                // 分数存在，修改评分
                homework_score.setUid(uid);
                homework_score.setCourse_id(course_id);
                homework_score.setHomework_id(homework_id);
                homework_score.setGroup_id(group_id);
                homework_score.setScore(score_to_set);
                homework_score.setGrader_id(uid);
                homework_score.setScore_message(score_message);
                scoreSerivce.updateScore(homework_score);
            }
            data.put("desc", "OK");
            response = setCorrectResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * API.20: 教师——学生提交的作业下载
     */
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
                                                       @RequestParam(value = "title", required = false) String resource_title,
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
//                resource_type = CodeConvert.unicode2String(resource_type);
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

        try {
            resourceService.deleteResource(resource_id);

            HashMap<String, Object> data = new HashMap<>();
            data.put("desc", "success");
            response = setCorrectResponse(data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        if (announcement == null || (!announcement.getCourse_id().equals(course_id))) {
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

    /**
     * API24: 查看已审批团队信息
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/group_confirm_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getGroupConfirm(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> group_confirm_list = groupConfirmService.getGroupConfirmList(course_id);

        data.put("group_confirm_list", group_confirm_list);
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API25: 查看未审批的团队信息
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/group_apply_list", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getGroupApply(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> group_apply_list = groupApplyService.getGroupApplyList(course_id);

        data.put("group_apply_list", group_apply_list);
        response = setCorrectResponse(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API26: 批准学生团队
     *
     * @param uid
     * @param course_id
     * @param group_apply_id
     * @return
     */
    @RequestMapping(value = "/group_confirm", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> confirmGroupApply(@RequestParam(value = "uid") String uid,
                                                          @RequestParam(value = "course_id") Integer course_id,
                                                          @RequestParam(value = "group_apply_id") Integer group_apply_id) {
        BaseResponse response = new BaseResponse();

        //判读group_apply_id合法性
        GroupApply groupApply = groupApplyService.findGroupApplyById(group_apply_id);
        if (groupApply == null || (!groupApply.getCourse_id().equals(course_id))) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //group_confirm 生成group_id 并插入记录
        GroupConfirm groupConfirm = new GroupConfirm();
        Integer gid = groupConfirmService.findMaxGroupId();

        groupConfirm.setGroup_id(gid);
        groupConfirm.setCourse_id(groupApply.getCourse_id());
        groupConfirm.setGroup_name(groupApply.getGroup_apply_name());
        groupConfirm.setGroup_owner_id(groupApply.getGroup_apply_owner_id());

        groupConfirmService.insertGroupConfirm(groupConfirm, uid);

        //group_confirm_memeber 插入记录
        List<GroupApplyMember> groupApplyMemberList = groupApplyMemberService.findGroupApplyMemberById(group_apply_id);
        if (groupApplyMemberList == null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        groupConfirmMemberService.insertGroupConfirmMember(groupApplyMemberList, uid, gid);

        //student 插入 group_id
        groupConfirmMemberService.updateStudentGroupId(gid);

        //group_apply 删除记录
        groupApplyService.deleteGroupApplyById(group_apply_id);

        //group_apply_member 删除记录
        groupApplyMemberService.deleteGroupApplyMemberByGroupApplyId(group_apply_id);

        //invitation 删除记录
        invitationService.deleteInvitationBySenderId(groupConfirm.getGroup_owner_id());

        response = setCorrectInsert();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API27: 拒绝学生团队
     *
     * @param uid
     * @param course_id
     * @param group_apply_id
     * @return
     */
    @RequestMapping(value = "/group_reject", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> rejectGroupApply(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "group_apply_id") Integer group_apply_id) {
        BaseResponse response = new BaseResponse();

        //判读group_apply_id合法性
        GroupApply groupApply = groupApplyService.findGroupApplyById(group_apply_id);
        if (groupApply == null || (!groupApply.getCourse_id().equals(course_id))) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //group_apply 删除记录
        groupApplyService.deleteGroupApplyById(group_apply_id);

        //group_apply_member 删除记录
        groupApplyMemberService.deleteGroupApplyMemberByGroupApplyId(group_apply_id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API28: 查看未组队人员
     *
     * @param course_id
     * @return
     */
    @RequestMapping(value = "/student_not_in_group", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getStudentNotInGroup(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> student_list = studentService.getStudentNotInGroup(course_id);
        data.put("student_list", student_list);

        response = setCorrectResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API30: 查看以往学期
     *
     * @return
     */
    @RequestMapping(value = "/old_course", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getOldCourse() {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        try {
            List<HashMap<String, Object>> courseList = courseService.getCourseList();
            data.put("course_list", courseList);
        } catch (ParseException parseException) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response = setCorrectResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
    *  API.32: 教师——获取团队成绩报表页面
    * */
    @RequestMapping(value = "/group_form", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getGroupForm (@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        try {
            List<HashMap<String, Object>> group_list = groupConfirmService.getGroupScoreList(course_id);
            data.put("group_list", group_list);
            response = setCorrectResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
    * API.33: 教师——获取个人成绩报表
    *
    * */
    @RequestMapping(value = "/student_form", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getStudentForm(@RequestParam(value = "course_id") Integer course_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();
        try {
            List<HashMap<String, Object>> student_score_list = new ArrayList<>();
            List<Student> students = studentService.findAllStudent();
            for (Student student : students) {
                if(student != null) {
                    HashMap<String, Object> student_map = new HashMap<>();
                    student_map.put("student_id", student.getStudent_id());
                    student_map.put("student_name", student.getStudent_name());
                    student_map.put("student_class", student.getStudent_class());
                    student_map.put("student_gender", student.getStudent_gender());
                    Double personal_score = studentService.setPersonalScoreById(student);
                    student_map.put("score", personal_score);
                    student_score_list.add(student_map);
                }
            }
            data.put("student_score_list", student_score_list);

            response = setCorrectResponse(data);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * API.49: 教师——录入缺勤信息 GET
    * */
    @RequestMapping(value = "/student_absent", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getStudentAbsent (@RequestParam(value = "uid") String uid) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        List<HashMap<String, Object>> student_absent_list = new ArrayList<>();

        List<Student> studentList = studentService.findAllStudent();
        Iterator<Student> student_iter = studentList.iterator();
        while (student_iter.hasNext()) {
            Student student = student_iter.next();
            HashMap<String, Object> student_map = new HashMap<>();
            student_map.put("student_id", student.getStudent_id());
            student_map.put("student_name", student.getStudent_name());
            student_map.put("student_absent", student.getStudent_absent());

            student_absent_list.add(student_map);
        }

        data.put("student_absent_list", student_absent_list);
        response = setCorrectResponse(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    /**
     * API.49: 教师——录入缺勤信息 POST
     * */
    @RequestMapping(value = "/student_absent", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> setStudentAbsent (@RequestParam(value = "uid") String uid,
                                                          @RequestParam(value = "student_id") String student_id,
                                                          @RequestParam(value = "student_absent") Integer student_absent) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        try {
            studentService.setStudentAbsentById(student_id, student_absent);
            data.put("desc", "success");
            response = setCorrectResponse(data);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API.29: 教师——下载团队信息
     * */
    @RequestMapping(value = "/group_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadGroupForm (
            @RequestParam(value = "course_id") Integer course_id) {
        try {
            String group_form_url = exportForm.exportGroupList();
            ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(group_form_url);
            org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

            HttpHeaders headers = getFileDownloadHeaders("团队组建报表.xls");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/x-msdownload"))
                    .body(resource);

        } catch (IOException | WriteException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API.51: 教师——下载团队成绩报表
     * */
    @RequestMapping(value = "/group_form_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadGroupScoreForm (
            @RequestParam(value = "course_id") Integer course_id) {
        try {
            String student_form_url = exportForm.exportGroupScoreList(course_id);
            ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(student_form_url);
            org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

            HttpHeaders headers = getFileDownloadHeaders("提交作业情况报表.xls");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/x-msdownload"))
                    .body(resource);

        } catch (IOException | WriteException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * API.52: 教师——下载个人成绩报表
     * */
    @RequestMapping(value = "/student_form_download", method = RequestMethod.GET)
    public ResponseEntity<org.springframework.core.io.Resource> downloadStudentScoreForm (
            @RequestParam(value = "course_id") Integer course_id) {
        try {
            String student_score_url = exportForm.exportStudentScoreList();
            ByteArrayOutputStream baos = FileUtils.getSingleDownloadFile(student_score_url);
            org.springframework.core.io.Resource resource = new InputStreamResource(new ByteArrayInputStream(baos.toByteArray()));

            HttpHeaders headers = getFileDownloadHeaders("个人成绩报表.xls");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/x-msdownload"))
                    .body(resource);

        } catch (IOException | WriteException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


