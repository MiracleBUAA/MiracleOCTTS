package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    private WebApplicationContext webApplicationContext;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private StudentService studentService;


    @Test
    public void testFindByIdSerivce() {
        Student student = studentService.findStudentById(14212333);
        assertNotNull(student);
    }

    @Test
    public void testFindByIdWhenLogin() {
        String uid = "14212333";
        Student student = studentService.findStudentByIdForLogin(uid);
        assertNotNull(student);
        assertEquals(student.getPassword(), "123456");
    }

    @Test
    public void testUploadFile() throws Exception {



        MockMultipartFile file = new MockMultipartFile("file", "test2333.txt",
                                            "text/plain", "上传测试2333".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/student/homework_upload")
                        .file(file)
                        .param("uid", "14212333")
                        .param("course_id", "1")
                        .param("homework_id", "1")
                        .param("group_id", "1"))
                .andExpect(status().is(200));
    }
}
