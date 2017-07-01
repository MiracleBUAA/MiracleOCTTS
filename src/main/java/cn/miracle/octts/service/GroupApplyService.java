package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyDao;
import cn.miracle.octts.entity.GroupApply;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
public class GroupApplyService {

    @Autowired
    private GroupApplyDao groupApplyDao;

    public List<String> findGroupApplyOwner(){
        return groupApplyDao.findGroupApplyOwner();
    }
}
