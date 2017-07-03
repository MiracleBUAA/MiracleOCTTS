package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkDao;
import cn.miracle.octts.dao.TeacherDao;
import cn.miracle.octts.entity.Homework;
import cn.miracle.octts.util.DateConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class HomeworkService {

    @Autowired
    private HomeworkDao homeworkDao;

    @Autowired
    private TeacherDao teacherDao;

    public Homework findHomeworkById(Integer homework_id) {
        return homeworkDao.findHomeworkById(homework_id);
    }

    public List<HashMap<String, Object>> getHomeworkList(Integer course_id) throws ParseException {
        List<HashMap<String, Object>> homework_list = new ArrayList<HashMap<String, Object>>();

        ArrayList<Homework> homeworks = new ArrayList<>();
        homeworks.addAll(homeworkDao.findHoweworkByCourseId(course_id));
        for (Homework homework : homeworks) {
            if (homework != null) {
                HashMap<String, Object> homework_item = new HashMap<>();
                homework_item.put("homework_id", homework.getHomework_id());
                homework_item.put("homework_title", homework.getHomework_title());

                homework_item.put("homework_start_time", DateConvert.datetime2String(homework.getHomework_start_time()));
                homework_item.put("homework_end_time", DateConvert.datetime2String(homework.getHomework_end_time()));

                homework_item.put("teacher_name", teacherDao.findTeacherNameById(homework.getTeacher_id()));

                homework_list.add(homework_item);
            }
        }
        return homework_list;
    }

    public void InsertHomework(Homework homework) {
        Date currenttime = new Date(System.currentTimeMillis());
        homework.setCreatetime(currenttime);
        homework.setUpdatetime(currenttime);
        homeworkDao.InsertHomework(homework);
    }

    public Integer findMaxHomeworkId() {
        Integer maxId = homeworkDao.findMaxHomeworkId();
        return maxId == null ? 1 : maxId;
    }

    public void updateHomework(Homework homework) {
        Date currenttime = new Date(System.currentTimeMillis());
        homework.setUpdatetime(currenttime);
        homeworkDao.updateHomework(homework);
    }

    public void deleteHomework(Integer homework_id) {
        homeworkDao.deleteHomework(homework_id);
    }

    public List<Integer> findAllHomeworkId() {
        return homeworkDao.findAllHomeworkId();
    }
}
