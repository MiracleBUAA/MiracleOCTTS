package cn.miracle.octts.dao;

import cn.miracle.octts.service.CourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import cn.miracle.octts.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by Tony on 2017/6/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseDaoTest {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseService courseService;

    private Date currentTime = new Date(System.currentTimeMillis());

    @Test
    public void testInsertCourse(){
        Course course = new Course();
        course.setCourse_start_time(currentTime);
        course.setCourse_end_time(currentTime);
        course.setCourse_name("软工");
        course.setCourse_hour(20);
        course.setCourse_location("317");
        course.setCreatetime(currentTime);
        course.setUpdatetime(currentTime);
        course.setUid("T001");
        course.setCourse_credit(100.0);
        courseDao.insertCourse(course);
    }

}
