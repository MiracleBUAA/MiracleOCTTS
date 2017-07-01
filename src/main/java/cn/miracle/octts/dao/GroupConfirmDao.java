package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupConfirm;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
public interface GroupConfirmDao extends BaseMapper<GroupConfirm> {

    @Select(value = "SELECT group_owner_id FROM group_confirm")
    List<String> findGroupOwner();
}
