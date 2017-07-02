package cn.miracle.octts.service;


import cn.miracle.octts.dao.GroupConfirmMemberDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<String> findGroupConfirmMemberListByGroupId(Integer group_id) {
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

    public Integer findGroupIdByStudentId(String student_id) {
        return groupConfirmMemberDao.findGroupIdByStudentId(student_id);
    }
}
