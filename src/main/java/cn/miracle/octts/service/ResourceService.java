package cn.miracle.octts.service;

import cn.miracle.octts.dao.ResourceDao;
import cn.miracle.octts.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class ResourceService {

    @Autowired
    private ResourceDao resourceDao;

    public Resource findByIdForDownload(Integer resource_id) {
        return resourceDao.findResourceById(resource_id);
    }

    public List<String> findResourceType() {
        return resourceDao.findResourceType();
    }
}
