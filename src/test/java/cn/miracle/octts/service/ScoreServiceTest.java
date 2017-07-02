package cn.miracle.octts.service;

import cn.miracle.octts.entity.Score;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by hf on 2017/7/2.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreServiceTest {

    @Autowired
    private ScoreSerivce scoreSerivce;

    @Test
    public void testFindByHidAndGid() {
        Score score = scoreSerivce.findScoreByHomeworkIdAndGroupId(1,1);
        assertNotNull(score);
    }
}
