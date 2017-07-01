package cn.miracle.octts.controller;

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
 * Created by hf on 2017/6/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void TestgetResource() {
        try {
            this.mockMvc.perform(get("/teacher/resource")
                    .param("course_id", "1"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestUploadResource() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "upload_test2333.txt",
                "text/plain", "这是一个上传测试2333".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/teacher/resource_upload")
                .file(file)
                .param("uid", "T001")
                .param("course_id", "1")
                .param("resource_type", "test")
                .param("title", "upload_test.txt"))
                .andExpect(status().is(200));
    }

    @Test
    public void TestAnnouncementInsert() throws Exception {
        this.mockMvc.perform(
                post("/teacher/new_announcement")
                        .param("uid", "T001")
                        .param("course_id","1")
                        .param("announcement_title","\\u8fd9\\u662f\\u4e00\\u4e2a\\u901a\\u77e5\\u20\\u31\\u32\\u33")
                        .param("announcement_message", "\\u8fd9\\u662f\\u901a\\u77e5\\u5185\\u5bb9\\u20\\u31\\u32\\u33"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TestUpdateAnnouncement() throws Exception {
        this.mockMvc.perform(
                post("/teacher/announcement_update")
                        .param("uid", "T001")
                        .param("course_id","1")
                        .param("announcement_id","2")
                        .param("announcement_title","\\u8fd9\\u662f\\u4e00\\u4e2a\\u901a\\u77e5\\u20\\u31\\u32\\u33")
                        .param("announcement_message", "\\u8fd9\\u662f\\u901a\\u77e5\\u5185\\u5bb9\\u20\\u31\\u32\\u33"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}

