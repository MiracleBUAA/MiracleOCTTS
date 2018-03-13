package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupConfirm;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hf on 2017/7/1.
 */
@MapperScan
@Component
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
            "WHERE course_id = #{course_id} " +
            "ORDER BY group_id")
    @ResultMap("cn.miracle.octts.dao.GroupConfirmDao.GroupConfirmDetail")
    List<GroupConfirm> findGroupConfirmByCourseId(Integer course_id);

    @Select("SELECT max(group_id) FROM group_confirm")
    Integer findMaxGroupId();

    @Select("SELECT gmt_create, gmt_modified, uid, group_id, course_id, group_name, group_owner_id, group_score " +
            "FROM group_confirm " +
            "WHERE group_owner_id=#{group_owner_id}")
    GroupConfirm findGroupConfirmByOwnerId(Integer group_owner_id);

    @Insert("INSERT INTO group_confirm(gmt_create, gmt_modified, uid, group_id, course_id, group_name, group_owner_id, group_score) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{group_id}, #{course_id}, #{group_name}, #{group_owner_id}, #{group_score})")
    Integer insertGroupConfirm(GroupConfirm groupConfirm);

    @Select("SELECT group_name FROM group_confirm WHERE group_id = #{group_id}")
    String findGroupConfirmNameById(Integer group_id);

}
