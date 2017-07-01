package cn.miracle.octts.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hf on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupAndGroupConfirmApplyTest {

    @Autowired
    GroupConfirmService groupConfirmService;
    @Autowired
    GroupApplyService groupApplyService;

    @Test
    public void findGroupOwnerTest() {
        ArrayList<String> groupOwners = new ArrayList<>();
        groupOwners.addAll(groupConfirmService.findGroupOwner());
        for (String groupowner : groupOwners) {
            System.out.println(groupowner);
        }
        ArrayList<String> groupApplyOwners = new ArrayList<>();
        groupApplyOwners.addAll(groupApplyService.findGroupApplyOwner());
        for (String group_apply_owner : groupApplyOwners) {
            System.out.println(group_apply_owner);
        }
    }
}
