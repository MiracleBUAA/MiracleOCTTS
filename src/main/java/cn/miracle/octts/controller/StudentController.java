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
    private HomeworkService homeworkService;

    @Autowired
    private HomeworkUploadService homeworkUploadService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private GroupApplyService groupApplyService;

    @Autowired
    private GroupApplyMemberService groupApplyMemberService;

    @Autowired
    private GroupConfirmMemberService groupConfirmMemberService;

    @Autowired
    private GroupConfirmService groupConfirmService;

    @Autowired
    private InvitationService invitationService;

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

    /**
     * API.35: 学生查看课程资源
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
     * API.36:学生下载课程资源
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
     * API.43: 学生——查看作业列表
     */
    @RequestMapping(value = "/homework_list", method = RequestMethod.GET)
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
     * API.44: 学生——作业信息
     */
    @RequestMapping(value = "/homework_information", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getHomeworkInformation(@RequestParam(value = "uid") String uid, // uid即学生id
                                                               @RequestParam(value = "course_id") Integer course_id,
                                                               @RequestParam(value = "homework_id") Integer homework_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        //生成学生作业提交信息

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
                return new ResponseEntity<>(response, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
            }

            //修改后的：写入group_list
            ArrayList<GroupConfirm> group_list = new ArrayList<>();
            Integer group_id = groupConfirmMemberService.findGroupIdByStudentId(uid);
            group_list.add(groupConfirmService.findGroupConfirmById(group_id));
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
     * 上传作业文件
     */
    @RequestMapping(value = "/homework_upload", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> UploadHomework(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "file") MultipartFile uploadFile,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "homework_id") Integer homework_id,
                                                       @RequestParam(value = "group_id") Integer group_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<>();

        if (uploadFile.isEmpty()) {
            response = setFileUploadError();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            // 完成文件上传
            try {
                // 将文件写入服务器
                String file_name = uploadFile.getOriginalFilename();
                String filePath = FileUtils.saveSingleUploadFile(uploadFile, FileUtils.HOMEWORK_UPLOAD_FOLDER);
                // TODO: WRITE DATABASE
                // Uid included
                HomeworkUpload homeworkUpload = new HomeworkUpload();
                homeworkUpload.setHomework_upload_id(homeworkUploadService.findMaxId());
                homeworkUpload.setHomework_id(homework_id);
                homeworkUpload.setCourse_id(course_id);
                homeworkUpload.setUid(uid);
                homeworkUpload.setGroup_id(group_id);
                homeworkUpload.setHomework_url(filePath);
                homeworkUpload.setFile_name(file_name);
                homeworkUploadService.InsertHomeworkUpload(homeworkUpload);
                data.put("desc", "OK");

                response = setCorrectResponse(data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (IOException e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

    }

    @RequestMapping(value = "/homework_delete", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> deleteHomeworkUpload(@RequestParam(value = "uid") String uid,
                                                             @RequestParam(value = "homework_upload_id") Integer homework_upload_id) {
        BaseResponse response = new BaseResponse();

        HomeworkUpload homeworkUploadToBeDelete = homeworkUploadService.findHomeworkUploadById(homework_upload_id);
        if (homeworkUploadToBeDelete == null) { // 作业不存在
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            try {
                homeworkUploadService.deleteHomeworkUploadById(homeworkUploadToBeDelete.getHomework_upload_id());

                HashMap<String, Object> data = new HashMap<>();
                data.put("desc", "OK");
                response = setCorrectResponse(data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<BaseResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    /**
     * API37: 查看我的团队
     *
     * @param course_id
     * @param student_id
     * @return
     */
    @RequestMapping(value = "/mygroup", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getMyGroup(@RequestParam(value = "course_id") Integer course_id,
                                                   @RequestParam(value = "student_id") String student_id) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        //已加入批准团队
        Integer group_id = groupConfirmMemberService.findGroupIdByStudentId(student_id);
        if (group_id != null) {
            GroupConfirm groupConfirm = groupConfirmService.findGroupConfirmById(group_id);
            if (groupConfirm == null || (!groupConfirm.getCourse_id().equals(course_id))) {
                response = setParamError();
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            } else {
                data = groupConfirmService.groupConfirm2Json(groupConfirm);
                data.put("group_status", "1");

                response = setCorrectResponse(data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        //已加入待审核团队
        Integer group_apply_id = groupApplyMemberService.findGroupApplyIdByStudentId(student_id);
        if (group_apply_id != null) {
            GroupApply groupApply = groupApplyService.findGroupApplyById(group_apply_id);
            if (groupApply == null || (!groupApply.getCourse_id().equals(course_id))) {
                response = setParamError();
                return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
            } else {
                data = groupApplyService.groupApply2Json(groupApply);
                data.put("group_status", "0");

                response = setCorrectResponse(data);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        //未加入团队
        response.setErrorNo(3);
        response.setErrorMsg("未加入团队");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API38: 创建新团队
     *
     * @param uid
     * @param course_id
     * @param group_name
     * @return
     */
    @RequestMapping(value = "/new_group", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> createGroup(@RequestParam(value = "uid") String uid,
                                                    @RequestParam(value = "course_id") Integer course_id,
                                                    @RequestParam(value = "group_name") String group_name) {
        BaseResponse response = new BaseResponse();
        //如果已经在团队中则不允许创建团队
        Integer group_id = groupConfirmMemberService.findGroupIdByStudentId(uid);
        Integer group_apply_id = groupApplyMemberService.findGroupApplyIdByStudentId(uid);
        if (group_id != null || group_apply_id != null) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        //插入 group_apply
        GroupApply groupApply = new GroupApply();

        Integer gaid = groupApplyService.findMaxGroupApplyId();

        groupApply.setGroup_apply_id(gaid);
        groupApply.setCourse_id(course_id);
        groupApply.setGroup_apply_name(CodeConvert.unicode2String(group_name));
        groupApply.setGroup_apply_owner_id(uid);

        groupApplyService.insetGroupApply(groupApply, uid);

        //插入 group_apply_member
        GroupApplyMember groupApplyMember = new GroupApplyMember();
        groupApplyMember.setGroup_apply_id(groupApplyService.findGroupApplyIdByGroupApplyOwnerId(uid));
        groupApplyMember.setCourse_id(course_id);
        groupApplyMember.setStudent_id(uid);
        groupApplyMember.setGroup_role(2);
        groupApplyMemberService.insertGroupApplyMember(groupApplyMember, uid);

        response = setCorrectInsert();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API39: 邀请页面
     *
     * @param course_id
     * @param uid
     * @return
     */
    @RequestMapping(value = "/invitation", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse> getInvitation(@RequestParam(value = "course_id") Integer course_id,
                                                      @RequestParam(value = "uid") String uid) {
        BaseResponse response = new BaseResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();

        //已加入已审批团队
        if (groupConfirmMemberService.findGroupIdByStudentId(uid) != null) {
            response.setErrorNo(4);
            response.setErrorMsg("团队已经批准");
            response.setData(new HashMap<>());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //已加入未审批团队
        GroupApplyMember groupApplyMember = groupApplyMemberService.findGroupApplyMemberByStudentId(uid);
        if (groupApplyMember != null && groupApplyMember.getCourse_id().equals(course_id)) {
            //是团队负责人
            if (groupApplyMember.getGroup_role().equals(2)) {
                List<HashMap<String, Object>> receiverList = studentService.getReceiverList(uid);
                data.put("invitation_list", receiverList);
                response = setCorrectResponse(data);

                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setErrorNo(5);
                response.setErrorMsg("已加入待审核团队");
                response.setData(new HashMap<>());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
        }

        //未加入团队
        List<HashMap<String, Object>> invitationList = invitationService.getInvitationList(uid);
        data.put("invitation_list", invitationList);
        response.setErrorNo(3);
        response.setErrorMsg("不是团队负责人");
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API40: 负责人查看未组队学生信息
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
     * API41 负责人发出邀请
     *
     * @param uid
     * @param course_id
     * @param receiver_id
     * @return
     */
    @RequestMapping(value = "/send_invitation", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> sendInvitation(@RequestParam(value = "uid") String uid,
                                                       @RequestParam(value = "course_id") Integer course_id,
                                                       @RequestParam(value = "receiver_id") String receiver_id) {
        BaseResponse response = new BaseResponse();
        //学生已经在其他队伍里
        if (!studentService.getStudentNotInGroupSet(course_id).contains(receiver_id)) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        //学生已经收到过此人的邀请
        if (invitationService.findSenderIdByReceiverId(receiver_id).contains(uid)) {
            response.setErrorNo(3);
            response.setErrorMsg("你已发出邀请");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        //新建邀请
        Invitation invitation = new Invitation();
        invitation.setSender_id(uid);
        invitation.setReceiver_id(receiver_id);
        invitationService.insertInvitation(invitation, uid);

        response = setCorrectInsert();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API42: 未组队学生接受邀请
     *
     * @param uid
     * @param course_id
     * @param sender_id
     * @return
     */
    @RequestMapping(value = "/accept_invitation", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> acceptInvitation(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "sender_id") String sender_id) {
        BaseResponse response = new BaseResponse();
        //学生已经在其他队伍里
        if (!studentService.getStudentNotInGroupSet(course_id).contains(uid)) {
            response = setParamError();
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }

        //插入 group_apply_member
        GroupApplyMember groupApplyMember = new GroupApplyMember();
        groupApplyMember.setGroup_apply_id(groupApplyService.findGroupApplyIdByGroupApplyOwnerId(sender_id));
        groupApplyMember.setCourse_id(course_id);
        groupApplyMember.setStudent_id(uid);
        groupApplyMember.setGroup_role(1);
        groupApplyMemberService.insertGroupApplyMember(groupApplyMember, uid);

        //T删除此人收到的所有邀请
        invitationService.deleteInvitationByReceiverId(uid);

        response = setCorrectInsert();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * API53 未组队学生拒绝邀请
     *
     * @param uid
     * @param course_id
     * @param sender_id
     * @return
     */
    @RequestMapping(value = "/reject_invitation", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse> rejectInvitation(@RequestParam(value = "uid") String uid,
                                                         @RequestParam(value = "course_id") Integer course_id,
                                                         @RequestParam(value = "sender_id") String sender_id) {
        BaseResponse response = new BaseResponse();

        invitationService.deleteInvitationBySenderIdAndReceiverId(sender_id, uid);

        response = setCorrectUpdate();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @RequestMapping(value = "/dismiss_group", method = RequestMethod.POST)
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


}