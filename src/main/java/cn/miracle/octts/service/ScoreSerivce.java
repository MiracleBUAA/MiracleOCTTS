package cn.miracle.octts.service;

import cn.miracle.octts.dao.ScoreDao;
import cn.miracle.octts.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by hf on 2017/7/2.
 */
@Service
public class ScoreSerivce {

    @Autowired
    private ScoreDao scoreDao;

    public Score findScoreByHomeworkIdAndGroupId(Integer homework_id, Integer group_id) {
        return scoreDao.findScoreByHomeworkIdAndGroupId(homework_id, group_id);
    }

    public void insertScore(Score score) {
        Integer maxId = scoreDao.findMaxId();
        Integer score_id = maxId == null ? 1 : maxId + 1;
        score.setScore_id(score_id);
        Date currentdate = new Date(System.currentTimeMillis());
        score.setCreatetime(currentdate);
        score.setUpdatetime(currentdate);
        scoreDao.InsertScore(score);
    }

    public void updateScore(Score score) {
        Date currentdate = new Date(System.currentTimeMillis());
        score.setUpdatetime(currentdate);
        scoreDao.updateScore(score);
    }
}
