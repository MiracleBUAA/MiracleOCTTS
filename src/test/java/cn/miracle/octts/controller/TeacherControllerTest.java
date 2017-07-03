package cn.miracle.octts.controller;

import cn.miracle.octts.util.CodeConvert;
import org.aspectj.apache.bcel.classfile.Code;
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
    public void testNewHomework() {
        String homework_title = CodeConvert.string2Unicode("测试作业");
        String homework_message = CodeConvert.string2Unicode("这是一个测试作业");
        String homework_start_time = CodeConvert.string2Unicode("2017-07-01 20:00:00");
        String homework_end_time = CodeConvert.string2Unicode("2017-07-02 20:00:00");
        try {
            this.mockMvc.perform(
                    post("/teacher/new_homework")
                            .param("uid", "T001")
                            .param("course_id", "1")
                            .param("homework_title", homework_title)
                            .param("homework_message", homework_message)
                            .param("homework_start_time", homework_start_time)
                            .param("homework_end_time", homework_end_time)
                            .param("homework_score", "10")
                            .param("resubmit_limit", "3"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateHomework() {
        String homework_title = CodeConvert.string2Unicode("测试作业");
        String homework_message = CodeConvert.string2Unicode("这是真的再次修改后的中文测试作业信息");
        String homework_start_time = CodeConvert.string2Unicode("2017-07-01 20:00:00");
        String homework_end_time = CodeConvert.string2Unicode("2017-07-02 20:00:00");
        try {
            this.mockMvc.perform(
                    post("/teacher/homework_update")
                            .param("uid", "T001")
                            .param("course_id", "1")
                            .param("homework_id", "4")
                            .param("homework_title", homework_title)
                            .param("homework_message", homework_message)
                            .param("homework_start_time", homework_start_time)
                            .param("homework_end_time", homework_end_time)
                            .param("homework_score", "10")
                            .param("resubmit_limit", "3"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteHomework() {
        try {
            this.mockMvc.perform(
                    post("/teacher/homework_delete")
                            .param("uid", "T001")
                            .param("homework_id", "4")
            )
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSetHomeworkScore() {
        String score_message = CodeConvert.string2Unicode("打得不错");
        try {
            this.mockMvc.perform(
                    post("/teacher/homework_set_score")
                            .param("uid","T001")
                            .param("course_id","1")
                            .param("homework_id", "2")
                            .param("group_id", "2")
                            .param("score", "20.0")
                            .param("score_message", score_message))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        MockMultipartFile file = new MockMultipartFile("file", "测试2.txt",
                "text/plain", "这是一个上传测试2333".getBytes());

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        String resource_type = CodeConvert.string2Unicode("测试");
        String name = CodeConvert.string2Unicode("测试2.txt");

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/teacher/resource_upload")
                .file(file)
                .param("uid", "T001")
                .param("course_id", "1")
                .param("resource_type", resource_type)
                .param("title", name))
                .andExpect(status().is(200));
    }

    @Test
    public void TestAnnouncementInsert() throws Exception {
        this.mockMvc.perform(
                post("/teacher/new_announcement")
                        .param("uid", "T001")
                        .param("course_id", "1")
                        .param("announcement_title", "\\u8fd9\\u662f\\u4e00\\u4e2a\\u901a\\u77e5\\u20\\u31\\u32\\u33")
                        .param("announcement_message", "\\u8fd9\\u662f\\u901a\\u77e5\\u5185\\u5bb9\\u20\\u31\\u32\\u33"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TestUpdateAnnouncement() throws Exception {
        this.mockMvc.perform(
                post("/teacher/announcement_update")
                        .param("uid", "T001")
                        .param("course_id", "1")
                        .param("announcement_id", "2")
                        .param("announcement_title", "\\u8fd9\\u662f\\u4e00\\u4e2a\\u901a\\u77e5\\u20\\u31\\u32\\u33")
                        .param("announcement_message", "\\u8fd9\\u662f\\u901a\\u77e5\\u5185\\u5bb9\\u20\\u31\\u32\\u33"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetGroupConfirm() {
        try {
            this.mockMvc.perform(get("/teacher/group_confirm_list")
                    .param("course_id", "1"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetGroupApply() {
        try {
            this.mockMvc.perform(get("/teacher/group_apply_list")
                    .param("course_id", "1"))
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConfirmGroupApply() throws Exception {
        this.mockMvc.perform(
                post("/teacher/group_confirm")
                        .param("uid", "T001")
                        .param("course_id", "1")
                        .param("group_apply_id", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}

