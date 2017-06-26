package cn.miracle.octts.service;

import static org.junit.Assert.*;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by hf on 2017/6/26.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentService studentService;


    @Test
    public void testFindById() {
        Student student = studentDao.findById(14212333);
        assertNotNull(student);
        assertEquals(student.getName(), "测试用户");
    }

    @Test
    public void testFindByIdSerivce() {
        Student student = studentService.findStudentById(14212333);
        assertNotNull(student);
    }
}
