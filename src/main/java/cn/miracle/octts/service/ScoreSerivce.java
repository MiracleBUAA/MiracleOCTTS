package cn.miracle.octts.service;

import cn.miracle.octts.dao.ScoreDao;
import cn.miracle.octts.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hf on 2017/7/2.
 */
@Service
public class ScoreSerivce {

    @Autowired
    private ScoreDao scoreDao;

    public Score findScoreByHomeworkIdAndGroupId(Integer homework_id, Integer group_id) {
        return scoreDao.findScoreByHomeworkIdandGrooupId(homework_id, group_id);
    }
}
