package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

    public List<String> findGroupApplyOwner() {
        return groupApplyDao.findGroupApplyOwner();
    }

    public HashMap<String, Object> groupApply2Json(GroupApply groupApply) {
        HashMap<String, Object> data = new HashMap<String, Object>();

        data.put("group_apply_id", groupApply.getGroup_apply_id());
        data.put("group_apply_name", groupApply.getGroup_apply_name());
        data.put("group_apply_owner", studentDao.findStudentNameById(groupApply.getGroup_apply_owner_id()));
        data.put("group_apply_member", groupApplyMemberService.findGroupApplyMemberNameByGroupApplyId(groupApply.getGroup_apply_id()));

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

}
