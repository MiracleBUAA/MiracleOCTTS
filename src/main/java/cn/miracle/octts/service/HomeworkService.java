package cn.miracle.octts.service;

import cn.miracle.octts.dao.HomeworkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class HomeworkService {

    @Autowired
    HomeworkDao homeworkDao;


}
