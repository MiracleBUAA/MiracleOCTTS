package cn.miracle.octts.dao;

import cn.miracle.octts.service.AnnouncementService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Tony on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnnouncementDaoTest {
    @Autowired
    private AnnouncementDao announcementDao;

    @Test
    public void testFindAnnouncementById() {
        assert(announcementDao.findAnnouncementById(new Integer(1)) != null);
    }
}
