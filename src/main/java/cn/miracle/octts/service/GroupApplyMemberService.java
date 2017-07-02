package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyMemberDao;
import cn.miracle.octts.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    List<String> findGroupApplyMemberListByGroupApplyId(Integer group_apply_id) {
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
}
