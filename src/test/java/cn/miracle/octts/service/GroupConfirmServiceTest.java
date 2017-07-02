package cn.miracle.octts.service;

import cn.miracle.octts.entity.GroupConfirm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Tony on 2017/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupConfirmServiceTest {

    @Autowired
    GroupConfirmService groupConfirmService;

    @Test
    public void testInsertGroupConfirm() {
        GroupConfirm groupConfirm = new GroupConfirm();
        groupConfirm.setGroup_id(groupConfirmService.findMaxGroupId());
        groupConfirm.setCourse_id(1);
        groupConfirm.setGroup_owner_id("14212333");
        groupConfirm.setGroup_name("测试组");

        groupConfirmService.insertGroupConfirm(groupConfirm, "123456");
    }

}
