package cn.miracle.octts.service;

import cn.miracle.octts.entity.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Test
    public void TestFindResourceType() {
        ArrayList<String> resource_types = new ArrayList<>();
        resource_types.addAll(resourceService.findResourceType(1));
        for (String type : resource_types) {
            System.out.println(type);
        }
    }

    @Test
    public void TestInsert() {
        Resource resource = new Resource();
        resource.setCourse_id(1);
        resource.setResource_id(5);
        resource.setResource_title("upload_test.txt");
        resource.setTeacher_id("T001");
        resource.setResource_type("test");
        resource.setResource_url("/Users/hf/tmp/upload/upload_test.txt");
        resource.setUid("T001");
        Date currenttime = new Date(System.currentTimeMillis());
        resource.setCreatetime(currenttime);
        resource.setUpdatetime(currenttime);
        resourceService.InsertResource(resource);
    }

    @Test
    public void TestDeleteResource() {
        try {
            resourceService.deleteResource(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
