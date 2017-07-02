package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupConfirmDao;
import cn.miracle.octts.entity.GroupConfirm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
@Service
public class GroupConfirmService {

    @Autowired
    private GroupConfirmDao groupConfirmDao;

    public List<String> findGroupOwner() {
        return groupConfirmDao.findGroupOwner();
    }

    public GroupConfirm findGroupConfirmById(Integer group_id) {
        return groupConfirmDao.findGroupConfirmById(group_id);
    }
}
