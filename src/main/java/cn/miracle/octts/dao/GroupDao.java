package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Group;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
public interface GroupDao extends BaseMapper<Group> {

    @Select(value = "SELECT group_owner_id FROM group")
    List<String> findGroupOwner();
}
