package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyMemberDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupApplyMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Tony on 2017/7/2.
 */
@Service
public class GroupApplyMemberService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupApplyMemberDao groupApplyMemberDao;

    public List<String> findGroupApplyMemberNameByGroupApplyId(Integer group_apply_id) {
        List<String> applyMemberList = new ArrayList<String>();

        List<String> studentIdList = groupApplyMemberDao.findStudentIdByGroupApplyId(group_apply_id);
        if (studentIdList != null) {
            Iterator<String> studentIdIter = studentIdList.iterator();

            while (studentIdIter.hasNext()) {
                applyMemberList.add(studentDao.findStudentNameById(studentIdIter.next()));
            }
        }

        return applyMemberList;
    }

    public List<GroupApplyMember> findGroupApplyMemberById(Integer group_apply_id) {
        return groupApplyMemberDao.findGroupApplyMemberById(group_apply_id);
    }

    public Integer deleteGroupApplyMemberByGroupApplyId(Integer group_apply_id) {
        return groupApplyMemberDao.deleteGroupApplyMemberByGroupApplyId(group_apply_id);
    }

    public Integer findGroupApplyIdByStudentId(String student_id) {
        return groupApplyMemberDao.findGroupApplyIdByStudentId(student_id);
    }

    public Integer findGroupRoleByStudentId(String student_id) {
        return groupApplyMemberDao.findGroupRoleByStudentId(student_id);
    }

    public GroupApplyMember findGroupApplyMemberByStudentId(String student_id) {
        return groupApplyMemberDao.findGroupApplyMemberByStudentId(student_id);
    }

    public Integer insertGroupApplyMember(GroupApplyMember groupApplyMember, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        groupApplyMember.setCreatetime(currentTime);
        groupApplyMember.setUpdatetime(currentTime);
        groupApplyMember.setUid(uid);

        return groupApplyMemberDao.insertGroupApplyMember(groupApplyMember);
    }
}
