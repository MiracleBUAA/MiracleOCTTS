package cn.miracle.octts.service;


import cn.miracle.octts.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Tony on 2017/7/2.
 */
@Service
public class GroupConfirmMemberService {

    @Autowired
    private StudentDao studentDao;

    /*TODO
    public String getGroupConfirmMemberByGroupConfirmId(Integer group_id) {
        List<String> student_name = studentDao.

        String groupMemberList = "";
        return groupMemberList;
    }*/


}
