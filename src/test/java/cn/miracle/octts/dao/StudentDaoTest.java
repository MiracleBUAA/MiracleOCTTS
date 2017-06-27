package cn.miracle.octts.dao;

import cn.miracle.octts.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hf on 2017/6/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDaoTest {

    @Autowired
    StudentDao studentDao;

    @Test
    public void testDaoMapping() {
        Student student = studentDao.findByIdforLogin(14212333);
        assertNotNull(student);
        assertEquals(student.getStudent_id(), "14212333");
        assertEquals(student.getPassword(), "123456");
    }
}
