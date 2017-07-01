package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupApply;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
public interface GroupApplyDao extends BaseMapper<GroupApply> {

    @Select("SELECT GROUP_APPLY_OWNER_ID FROM GROUP_APPLY")
    List<String> findGroupApplyOwner();

}
