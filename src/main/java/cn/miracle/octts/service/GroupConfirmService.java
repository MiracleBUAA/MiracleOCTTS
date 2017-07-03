package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupConfirmDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
