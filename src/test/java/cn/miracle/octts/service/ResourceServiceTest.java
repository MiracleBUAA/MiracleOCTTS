package cn.miracle.octts.service;

import cn.miracle.octts.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
/**
 * Created by hf on 2017/6/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ResourceServiceTest {

    @Autowired
    private ResourceService resourceService;

    @Test
    public void TestFindByIdForDownload() {
        Resource resource = resourceService.findByIdForDownload(1);
        assertNotNull(resource);
        assertEquals(resource.getResource_url(), "/Users/hf/tmp/test/test.txt");
    }

}
