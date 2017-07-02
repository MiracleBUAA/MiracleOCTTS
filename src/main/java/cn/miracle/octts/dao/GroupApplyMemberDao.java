package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupApplyMember;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tony on 2017/7/2.
 */
public interface GroupApplyMemberDao extends BaseMapper<GroupApplyMember> {

    @Select("SELECT student_id FROM group_apply_member WHERE group_apply_id = #{group_apply_id}")
    List<String> findStudentIdByGroupApplyId(Integer group_apply_id);
}
