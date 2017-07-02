package cn.miracle.octts.service;


import cn.miracle.octts.dao.GroupConfirmMemberDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.GroupApplyMember;
import cn.miracle.octts.entity.GroupConfirmMember;
import cn.miracle.octts.entity.Student;
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
public class GroupConfirmMemberService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private GroupConfirmMemberDao groupConfirmMemberDao;

    public List<String> findGroupConfirmMemberNameByGroupId(Integer group_id) {
        List<String> memberList = new ArrayList<String>();

        List<String> studentIdList = groupConfirmMemberDao.findStudentIdByGroupId(group_id);
        if (studentIdList != null) {
            Iterator<String> studentIdIter = studentIdList.iterator();

            while (studentIdIter.hasNext()) {
                memberList.add(studentDao.findStudentNameById(studentIdIter.next()));
            }
        }

        return memberList;
    }

    public List<String> findStudentIdByGroupId(Integer group_id) {
        return groupConfirmMemberDao.findStudentIdByGroupId(group_id);
    }

    public Integer updateStudentGroupId(Integer group_id) {
        Integer countUpdateStudentGroupId = 0;
        List<String> studentIdList = findStudentIdByGroupId(group_id);
        Iterator<String> studentIdIter = studentIdList.iterator();
        while (studentIdIter.hasNext()) {
            Student student = studentDao.findStudentById(studentIdIter.next());
            student.setGroup_id(group_id);
            studentDao.updateGroupId(student);
            countUpdateStudentGroupId += 1;
        }
        return countUpdateStudentGroupId;
    }

    public Integer insertGroupConfirmMember(List<GroupApplyMember> groupApplyMemberList, String uid, Integer group_id) {
        Integer countGroupConfirmMember = 0;
        Iterator<GroupApplyMember> groupApplyMemberIter = groupApplyMemberList.iterator();
        while (groupApplyMemberIter.hasNext()) {
            GroupApplyMember groupApplyMember = groupApplyMemberIter.next();
            GroupConfirmMember groupConfirmMember = new GroupConfirmMember();

            groupConfirmMember.setGroup_id(group_id);
            groupConfirmMember.setCourse_id(groupApplyMember.getCourse_id());
            groupConfirmMember.setStudent_id(groupApplyMember.getStudent_id());
            groupConfirmMember.setGroup_role(groupApplyMember.getGroup_role());

            Date currentTime = new Date(System.currentTimeMillis());
            groupConfirmMember.setCreatetime(currentTime);
            groupConfirmMember.setUpdatetime(currentTime);
            groupConfirmMember.setUid(uid);
            groupConfirmMemberDao.insertGroupConfirmMember(groupConfirmMember);
            countGroupConfirmMember += 1;
        }
        return countGroupConfirmMember;
    }

    public Integer findGroupIdByStudentId(String student_id) {
        return groupConfirmMemberDao.findGroupIdByStudentId(student_id);
    }
}
