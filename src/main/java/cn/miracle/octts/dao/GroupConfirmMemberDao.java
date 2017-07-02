package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupConfirmMember;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tony on 2017/7/2.
 */
public interface GroupConfirmMemberDao extends BaseMapper<GroupConfirmMember> {

    @Select("SELECT student_id FROM group_confirm_member WHERE group_id = #{group_id}")
    List<String> findStudentIdByGroupId(Integer group_id);

}
