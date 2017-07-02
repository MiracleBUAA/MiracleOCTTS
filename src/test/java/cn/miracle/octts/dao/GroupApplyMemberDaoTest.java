package cn.miracle.octts.dao;

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
public class GroupApplyMemberDaoTest {

    @Autowired
    private GroupApplyMemberDao groupApplyMemberDao;

    @Test
    public void testFindGroupApplyMemberById() {
        groupApplyMemberDao.findGroupApplyMemberById(1);
    }

}
