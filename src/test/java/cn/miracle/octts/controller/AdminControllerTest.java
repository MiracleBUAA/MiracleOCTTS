package cn.miracle.octts.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tony on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void TestCourseInfomationInsert() throws Exception {
        this.mockMvc.perform(
                post("/admin/new_course")
                        .param("uid", "T001")
                        .param("course_year","2017")
                        .param("course_name", "\\u6625\\u5b63\\u8f6f\\u4ef6\\u5f00\\u53d1\\u5b9e\\u8df5\\u4e0a\\u5b66\\u671f")
                        .param("course_start_time", "2017-06-25")
                        .param("course_hour", "120")
                        .param("course_location", "\\u5de5\\u8bad\\u33\\u31\\u37")
                        .param("course_credit", "2")
                        .param("teacher_information", "\\u6797\\u5e7f\\u8273"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TestCourseInformationUpdate() throws Exception {
        this.mockMvc.perform(
                post("/admin/course_update")
                        .param("uid", "T001")
                        .param("course_id","3")
                        .param("course_year","2017")
                        .param("course_name", "\\u6625\\u5b63\\u8f6f\\u4ef6\\u5f00\\u53d1\\u5b9e\\u8df5\\u4e0a\\u5b66\\u671f")
                        .param("course_start_time", "2017-06-25")
                        .param("course_hour", "80")
                        .param("course_location", "\\u5de5\\u8bad\\u33\\u31\\u37")
                        .param("course_credit", "2")
                        .param("teacher_information", "\\u6797\\u5e7f\\u8273"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void TestEndCourse() throws Exception {
        this.mockMvc.perform(
                post("/admin/course_end")
                        .param("uid", "T001")
                        .param("course_id","3"))
                .andDo(print())
                .andExpect(status().isOk());
    }



}
