package cn.miracle.octts.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Created by hf on 2017/6/28.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestCourseInfoUpdate() throws Exception {
        this.mockMvc.perform(
                post("/teacher/course_information")
                        .param("uid", "T001")
                        .param("course_id", "1")
                        .param("course_name", "\\u6625\\u5b63\\u8f6f\\u4ef6\\u5f00\\u53d1\\u5b9e\\u8df5\\u32\\u33\\u33\\u33")
                        .param("course_start_time", "2017-06-25")
                        .param("course_end_time", "2017-07-05")
                        .param("course_hour", "100")
                        .param("course_location", "\\u5de5\\u8bad\\u34\\u31\\u37")
                        .param("credit", "2")
                        .param("team_limit_information", "\\u36\\u2d\\u31\\u30\\u4eba")
                        .param("teacher_information", "\\u6797\\u5e7f\\u8273")
                        .param("course_information", "\\u7b7e\\u5230\\u5f88\\u4e25\\u683c"))
                .andDo(print())
        .andExpect(status().isOk());
    }


}
