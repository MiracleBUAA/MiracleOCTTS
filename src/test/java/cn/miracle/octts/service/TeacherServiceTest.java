package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Tony on 2017/6/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Test
    public void TestImportStudentList() {
        String file_url = "/Users/hf/tmp/upload/student_list";
        int count = teacherService.importStudentList(file_url);
        assertNotNull(count);
    }
}
