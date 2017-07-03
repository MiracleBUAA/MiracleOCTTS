package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupConfirmDao;
import cn.miracle.octts.dao.HomeworkUploadDao;
import cn.miracle.octts.dao.ScoreDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupConfirm;
import cn.miracle.octts.entity.HomeworkUpload;
import cn.miracle.octts.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by hf on 2017/7/1.
 */
@Service
public class GroupConfirmService {

    @Autowired
    private GroupConfirmDao groupConfirmDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupConfirmMemberService groupConfirmMemberService;

    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private HomeworkUploadDao homeworkUploadDao;

    @Autowired
    private HomeworkUploadService homeworkUploadService;


    public List<String> findGroupOwner() {
        return groupConfirmDao.findGroupOwner();
    }

    public GroupConfirm findGroupConfirmById(Integer group_id) {
        return groupConfirmDao.findGroupConfirmById(group_id);
    }

    public List<GroupConfirm> findGroupConfirmByCourseId(Integer course_id) {
        return groupConfirmDao.findGroupConfirmByCourseId(course_id);
    }

    public HashMap<String, Object> groupConfirm2Json(GroupConfirm group_confirm) {
        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("group_id", group_confirm.getGroup_id());
        data.put("group_name", group_confirm.getGroup_name());
        data.put("group_owner_name", studentDao.findStudentNameById(group_confirm.getGroup_owner_id()));
        data.put("group_member", groupConfirmMemberService.findGroupConfirmMemberNameByGroupId(group_confirm.getGroup_id()));

        return data;
    }

    public List<HashMap<String, Object>> getGroupConfirmList(Integer course_id) {
        List<HashMap<String, Object>> group_confirm_list = new ArrayList<HashMap<String, Object>>();

        List<GroupConfirm> group_confirm_result = findGroupConfirmByCourseId(course_id);
        Iterator<GroupConfirm> group_confirm_iter = group_confirm_result.iterator();
        while (group_confirm_iter.hasNext()) {
            HashMap<String, Object> group_confirm = groupConfirm2Json(group_confirm_iter.next());
            group_confirm_list.add(group_confirm);
        }
        return group_confirm_list;
    }

    public Integer findMaxGroupId() {
        if (groupConfirmDao.findMaxGroupId() != null) {
            return groupConfirmDao.findMaxGroupId() + 1;
        }
        return new Integer(1);
    }

    public Integer insertGroupConfirm(GroupConfirm groupConfirm, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        groupConfirm.setCreatetime(currentTime);
        groupConfirm.setUpdatetime(currentTime);
        groupConfirm.setUid(uid);
        return groupConfirmDao.insertGroupConfirm(groupConfirm);
    }

//    public GroupConfirm findGroupConfirmByOwner

    public List<HashMap<String, Object>> getGroupHomeworkList(ArrayList<GroupConfirm> group_list, Integer homework_id) throws ParseException{
        List<HashMap<String, Object>> homework_group_list = new ArrayList<HashMap<String, Object>>();

        for (GroupConfirm group : group_list) {
            HashMap<String, Object> group_map = new HashMap<>();
            // 团队信息
            group_map.put("group_id", group.getGroup_id());
            group_map.put("group_name", group.getGroup_name());
            group_map.put("group_owner", studentDao.findStudentNameById(group.getGroup_owner_id()));
            // 团队作业评分
            Integer group_id = group.getGroup_id();
            Score score = scoreDao.findScoreByHomeworkIdAndGroupId(homework_id, group_id);
            group_map.put("score", score.getScore());
            group_map.put("score_message", score.getScore_message());

            // 作业列表
            ArrayList<HomeworkUpload> homework_uploads = new ArrayList<>();
            homework_uploads.addAll(homeworkUploadDao.findHomeworkUploadByHomeworkIdAndGroupId(homework_id, group_id));
            ArrayList<HashMap<String, Object>> homework_upload_list = new ArrayList<>();
            homework_upload_list.addAll(homeworkUploadService.getHomeworkUploadList(homework_uploads));
            group_map.put("homework_upload_list", homework_upload_list);

            // 写入homework_group_list
            homework_group_list.add(group_map);
        }

        return homework_group_list;
    }
}
