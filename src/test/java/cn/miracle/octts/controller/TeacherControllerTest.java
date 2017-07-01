package cn.miracle.octts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
}

