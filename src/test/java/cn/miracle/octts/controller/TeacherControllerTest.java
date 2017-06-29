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
                        .param("course_name", "春季软件开发实践2333")
                        .param("course_start_time", "2017-06-25")
                        .param("course_end_time", "2017-07-05")
                        .param("course_hours", "120")
                        .param("course_location", "工训317")
                        .param("credit", "2")
                        .param("team_limit_information", "6-8人")
                        .param("teacher_information", "林广艳")
                        .param("course_information", "签到很严格"))
                .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    public void TestCourseInfoInsert() throws Exception {
        this.mockMvc.perform(
                post("/teacher/new_course")
                        .param("uid", "T001")
                        .param("course_name", "春季软件开发实践上学期")
                        .param("course_start_time", "2017-06-25")
                        .param("course_end_time", "2017-07-05")
                        .param("course_hours", "120")
                        .param("course_location", "工训317")
                        .param("credit", "2")
                        .param("team_limit_information", "6-8人")
                        .param("teacher_information", "林广艳")
                        .param("course_information", "签到很严格"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
