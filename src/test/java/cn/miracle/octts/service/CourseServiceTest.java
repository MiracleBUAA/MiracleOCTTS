package cn.miracle.octts.service;

import cn.miracle.octts.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hf on 2017/6/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    public void TestFindCourseById() {
        Course course = courseService.findCourseById(1);
        assertNotNull(course);
    }
}
