package cn.miracle.octts.service;

import cn.miracle.octts.dao.ResourceDao;
import cn.miracle.octts.dao.TeacherDao;
import cn.miracle.octts.entity.Resource;
import cn.miracle.octts.util.DateConvert;
import cn.miracle.octts.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private TeacherDao teacherDao;

    public Resource findByIdForDownload(Integer resource_id) {
        return resourceDao.findResourceById(resource_id);
    }

    public List<String> findResourceType(Integer course_id) {
        return resourceDao.findResourceType(course_id);
    }

    public List<HashMap<String, Object>> getResourceList(Integer course_id) throws ParseException{
        List<HashMap<String, Object>> resource_list = new ArrayList<HashMap<String, Object>>();

        ArrayList<Resource> resources = new ArrayList<>();

        resources.addAll(resourceDao.findResourceByCourseId(course_id));
        for (Resource resource : resources) {
            if (resource != null) {
                HashMap<String, Object> resource_item = new HashMap<>();

                resource_item.put("resource_id", resource.getResource_id());
                resource_item.put("resource_title", resource.getResource_title());
                resource_item.put("resource_type", resource.getResource_type());
                resource_item.put("create_time", DateConvert.datetime2String(resource.getCreatetime()));
                String teacher_name = teacherDao.findTeacherNameById(resource.getTeacher_id());
                resource_item.put("teacher_name", teacher_name);

                resource_list.add(resource_item);
            }
        }
        return resource_list;
    }

    public void InsertResource(Resource resource) {
        Date currenttime = new Date(System.currentTimeMillis());
        resource.setCreatetime(currenttime);
        resource.setUpdatetime(currenttime);
        resourceDao.InsertResource(resource);
    }

    public Integer findMaxResource() {
        Integer maxId = resourceDao.findMAxResourceId();
        return maxId == null ? 1 : maxId;
    }

    public void deleteResource(Integer resource_id) throws IOException{
        String resource_url = resourceDao.findResourceById(resource_id).getResource_url();
        FileUtils.deleteSingleFile(resource_url);
        resourceDao.deleteResourceById(resource_id);
    }
}
