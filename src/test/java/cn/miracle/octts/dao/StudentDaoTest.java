package cn.miracle.octts.dao;

import cn.miracle.octts.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

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
        assertEquals(student.getPassword(), "14212333");
    }

    @Test
    public void testInsertStudent() {
        Date currentTime = new Date(System.currentTimeMillis());

        Student student = new Student();
        student.setStudent_id("14212333");
        student.setPassword(student.getStudent_id());
        student.setStudent_class("142115");
        student.setGender('1');
        student.setName("测试用户");
        student.setCreatetime(currentTime);
        student.setUpdatetime(currentTime);

        int sum = studentDao.insertStudent(student);
    }
}
