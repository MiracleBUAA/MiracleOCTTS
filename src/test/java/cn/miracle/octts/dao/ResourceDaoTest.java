package cn.miracle.octts.dao;

import cn.miracle.octts.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

/**
 * Created by hf on 2017/7/1.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceDaoTest {
    @Autowired
    private ResourceDao resourceDao;

    @Test
    public void TestfindResourceList() {
        ArrayList<Resource> resources = new ArrayList<>();
        resources.addAll(resourceDao.findResourceByCourseId(1));
        assertEquals(resources.size(), 2);
    }
}
