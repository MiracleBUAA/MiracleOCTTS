package cn.miracle.octts.service;

import cn.miracle.octts.dao.ResourceDao;
import cn.miracle.octts.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hf on 2017/6/28.
 */
@Service
public class ResourceService {

    @Autowired
    ResourceDao resourceDao;

    public Resource findByIdForDownload(Integer resource_id) {
        return resourceDao.findByIdForDownload(resource_id);
    }
}
