package cn.miracle.octts.service;

import cn.miracle.octts.entity.GroupApplyMember;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2017/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupConfirmMemberServiceTest {

    @Autowired
    GroupConfirmMemberService groupConfirmMemberService;

    @Autowired
    GroupConfirmService groupConfirmService;

    @Test
    public void testInsertGroupConfirmMember() {
        List<GroupApplyMember> groupApplyMemberList = new ArrayList<GroupApplyMember>();

        GroupApplyMember groupApplyMember = new GroupApplyMember();
        groupApplyMember.setCourse_id(1);
        groupApplyMember.setGroup_apply_id(1);
        groupApplyMember.setGroup_role(2);
        groupApplyMember.setStudent_id("14212333");

        groupApplyMemberList.add(groupApplyMember);

        groupConfirmMemberService.insertGroupConfirmMember(groupApplyMemberList ,"T123", groupConfirmService.findMaxGroupId());
    }

    @Test
    public void testFindGidBySid() {
        Integer gid = groupConfirmMemberService.findGroupIdByStudentId("14215555");
        assertEquals(gid, new Integer(1));
    }
}
