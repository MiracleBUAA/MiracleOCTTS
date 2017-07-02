package cn.miracle.octts.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2017/7/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GroupConfirmMemberDaoTest {
    @Autowired
    private GroupConfirmMemberDao groupConfirmMemberDao;

    @Test
    public void testFindStudentIdByGroupId() {
        List<String> memberList = groupConfirmMemberDao.findStudentIdByGroupId(1);
        assertNotNull(memberList);
        List<String> expect = new ArrayList<String>();
        expect.add("14215555");
        expect.add("14217777");
        assertEquals(expect, memberList);
    }


}
