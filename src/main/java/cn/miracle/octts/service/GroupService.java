package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    public List<String> findGroupOwner() {
        return groupDao.findGroupOwner();
    }
}
