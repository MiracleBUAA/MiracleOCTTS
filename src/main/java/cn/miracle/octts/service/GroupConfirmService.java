package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
@Service
public class GroupConfirmService {

    @Autowired
    private GroupDao groupDao;

    public List<String> findGroupOwner() {
        return groupDao.findGroupOwner();
    }
}
