package cn.miracle.octts.service;

import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Teacher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        String uid = "T001";
        int count = teacherService.importStudentList(file_url, uid);
        assertNotNull(count);
    }

    @Test
    public void TestUploadStudentList() throws FileNotFoundException, Exception {

        byte[] filebytes;
        FileInputStream fs = new FileInputStream("/Users/hf/tmp/test/student_list.xls");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fs);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int c = bufferedInputStream.read();
        while (c != -1) {
            byteArrayOutputStream.write(c);
            c = bufferedInputStream.read();
        }
        bufferedInputStream.close();

        filebytes = byteArrayOutputStream.toByteArray();

        MockMultipartFile file = new MockMultipartFile("file", "student_list.xls",
                "application/x-xls", filebytes);

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/teacher/student_list")
                .file(file)
                .param("course_id", "1"))
                .andExpect(status().is(200));
    }
}
