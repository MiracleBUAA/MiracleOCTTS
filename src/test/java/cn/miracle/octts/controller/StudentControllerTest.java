package cn.miracle.octts.controller;

import cn.miracle.octts.util.CodeConvert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tony on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void TestCourseInfomationInsert() throws Exception {
        this.mockMvc.perform(
                get("/student/resource_download")
                        .param("resource_id", "3"));
    }

    @Test
    public void testCreateGroup() throws Exception {
        this.mockMvc.perform(
                post("/student/new_group")
                        .param("uid", "14213333")
                        .param("course_id", "1")
                        .param("group_name", "测试创建组"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testHomeworkUpload() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "作业测试",
                "text/plain", "这是一个上传作业测试2333".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String resource_type = CodeConvert.string2Unicode("作业测试");
        String name = CodeConvert.string2Unicode("作业测试.txt");

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/student/homework_upload")
                .file(file)
                .param("uid", "T001")
                .param("course_id", "1")
                .param("homework_id", "1")
                .param("group_id", "1"))
                .andDo(print())
                .andExpect(status().is(200));
    }

    @Test
    public void testDeleteHomeworkUpload () throws Exception {
        this.mockMvc.perform(
                post("/student/homework_delete")
                        .param("uid", "T001")
                        .param("homework_upload_id", "3"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSendInvitation() throws Exception {
        this.mockMvc.perform(
                post("/student/send_invitation")
                        .param("uid", "14214243")
                        .param("course_id", "1")
                        .param("receiver_id", "14213333"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
