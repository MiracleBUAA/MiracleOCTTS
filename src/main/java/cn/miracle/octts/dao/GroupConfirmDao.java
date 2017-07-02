package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupConfirm;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */

public interface GroupConfirmDao extends BaseMapper<GroupConfirm> {

    @Select("SELECT group_owner_id FROM group_confirm")
    List<String> findGroupOwner();

    @Select("SELECT gmt_create, gmt_modified, uid, group_id, course_id, group_name, group_owner_id, group_score " +
            "FROM group_confirm " +
            "WHERE group_id = #{group_id}")
    @ResultMap("cn.miracle.octts.dao.GroupConfirmDao.GroupConfirmDetail")
    GroupConfirm findGroupConfirmById(Integer group_id);

    @Select("SELECT gmt_create, gmt_modified, uid, group_id, course_id, group_name, group_owner_id, group_score " +
            "FROM group_confirm " +
            "WHERE course_id = #{course_id}")
    @ResultMap("cn.miracle.octts.dao.GroupConfirmDao.GroupConfirmDetail")
    List<GroupConfirm> findGroupConfirmByCourseId(Integer course_id);

}
