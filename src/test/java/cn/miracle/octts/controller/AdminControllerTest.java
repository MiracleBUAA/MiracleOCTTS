package cn.miracle.octts.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    public void TestInitCourseInfomation() throws Exception {
        this.mockMvc.perform(
                post("/admin/new_course")
                        .param("uid", "T001")
                        .param("course_year","2017")
                        .param("course_name", "春季软件开发实践上学期")
                        .param("course_start_time", "2017-06-25")
                        .param("course_hour", "120")
                        .param("course_location", "工训317")
                        .param("course_credit", "2")
                        .param("teacher_information", "林广艳"))
                .andDo(print())
                .andExpect(status().isOk());
    }


}
