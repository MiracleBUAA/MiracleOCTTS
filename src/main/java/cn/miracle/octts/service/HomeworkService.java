package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkDao;
import cn.miracle.octts.entity.Homework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class HomeworkService {

    @Autowired
    HomeworkDao homeworkDao;

    public void InsertHomwork(Homework homework) {
        Date currenttime = new Date();
        homework.setCreatetime(currenttime);
        homework.setUpdatetime(currenttime);
        homeworkDao.InsertHomework(homework);
    }

    public Integer findMaxHomeworkId() {
        Integer maxId = homeworkDao.findMaxHomeworkId();
        return maxId == null ? 1 : maxId;
    }
}
