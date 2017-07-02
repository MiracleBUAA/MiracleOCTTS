package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkDao;
import cn.miracle.octts.entity.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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

    public Homework findHomeworkById(Integer homework_id) {
        return homeworkDao.findHomeworkById(homework_id);
    }

    public List< HashMap<String, Object> > getHomeworkList(Integer course_id) {
        List<HashMap<String, Object>> homework_list = new ArrayList<HashMap<String, Object>>();

        ArrayList<Homework> homeworks = new ArrayList<>();
        homeworks.addAll(homeworkDao.findHoweworkByCourseId(course_id));
        for (Homework homework : homeworks) {
            HashMap<String, Object> homework_item = new HashMap<>();
            homework_item.put("homework_id", homework.getHomework_id());
            homework_item.put("homework_title", homework.getHomework_title());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String start_time = sdf.format(homework.getHomework_start_time());
            String end_time = sdf.format(homework.getHomework_end_time());
            homework_item.put("homework_start_time", start_time);
            homework_item.put("homework_end_time", end_time);

            homework_item.put("teacher_id", homework.getTeacher_id());

            homework_list.add(homework_item);
        }
        return homework_list;
    }

    public void InsertHomework(Homework homework) {
        Date currenttime = new Date();
        homework.setCreatetime(currenttime);
        homework.setUpdatetime(currenttime);
        homeworkDao.InsertHomework(homework);
    }

    public Integer findMaxHomeworkId() {
        Integer maxId = homeworkDao.findMaxHomeworkId();
        return maxId == null ? 1 : maxId;
    }

    public void updateHomework(Homework homework) {
        homework.setUpdatetime(new Date());
        homeworkDao.updateHomework(homework);
    }
}
