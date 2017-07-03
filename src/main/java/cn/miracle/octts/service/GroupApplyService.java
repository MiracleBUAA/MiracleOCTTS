package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by hf on 2017/7/1.
 */
@Service
public class GroupApplyService {

    @Autowired
    private GroupApplyDao groupApplyDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupApplyMemberService groupApplyMemberService;

    @Autowired
    private StudentService studentService;

    public List<String> findGroupApplyOwner() {
        return groupApplyDao.findGroupApplyOwner();
    }

    public HashMap<String, Object> groupApply2Json(GroupApply groupApply) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        Integer gaid = groupApply.getGroup_apply_id();

        data.put("group_id", groupApply.getGroup_apply_id());
        data.put("group_name", groupApply.getGroup_apply_name());
        data.put("group_owner_name", studentDao.findStudentNameById(groupApply.getGroup_apply_owner_id()));
        data.put("group_member", studentService.getMemberList(groupApplyMemberService.findStudentIdByGroupApplyId(gaid)));

        return data;
    }

    public List<HashMap<String, Object>> getGroupApplyList(Integer course_id) {
        List<HashMap<String, Object>> groupApplyList = new ArrayList<HashMap<String, Object>>();

        List<GroupApply> groupApplyResult = groupApplyDao.findGroupApplyByCourseId(course_id);
        Iterator<GroupApply> groupApplyIter = groupApplyResult.iterator();
        while (groupApplyIter.hasNext()) {
            HashMap<String, Object> group_apply = groupApply2Json(groupApplyIter.next());
            groupApplyList.add(group_apply);
        }

        return groupApplyList;
    }

    public GroupApply findGroupApplyById(Integer group_apply_id) {
        return groupApplyDao.findGroupApplyById(group_apply_id);
    }

    public Integer deleteGroupApplyById(Integer group_apply_id) {
        return groupApplyDao.deleteGroupApplyById(group_apply_id);
    }

    public Integer findMaxGroupApplyId() {
        if (groupApplyDao.findMaxGroupApplyId() != null) {
            return groupApplyDao.findMaxGroupApplyId() + 1;
        } else {
            return new Integer(1);
        }
    }

    public Integer insetGroupApply(GroupApply groupApply, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        groupApply.setCreatetime(currentTime);
        groupApply.setUpdatetime(currentTime);
        groupApply.setUid(uid);

        return groupApplyDao.insertGroupApply(groupApply);
    }

    public Integer findGroupApplyIdByGroupApplyOwnerId(String group_apply_owner_id) {
        return groupApplyDao.findGroupApplyIdByGroupApplyOwnerId(group_apply_owner_id);
    }

}
