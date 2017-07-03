package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupApplyMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tony on 2017/7/2.
 */
public interface GroupApplyMemberDao extends BaseMapper<GroupApplyMember> {

    @Select("SELECT student_id FROM group_apply_member WHERE group_apply_id = #{group_apply_id}")
    List<String> findStudentIdByGroupApplyId(Integer group_apply_id);

    @Select("SELECT gmt_create, gmt_modified, uid, group_apply_id, course_id, student_id, group_role " +
            "FROM group_apply_member " +
            "WHERE group_apply_id = #{group_apply_id}")
    @ResultMap("cn.miracle.octts.dao.GroupApplyMemberDao.GroupApplyMemberDetail")
    List<GroupApplyMember> findGroupApplyMemberById(Integer group_apply_id);

    @Delete("DELETE FROM group_apply_member WHERE group_apply_id = #{group_apply_id}")
    Integer deleteGroupApplyMemberByGroupApplyId(Integer group_apply_id);

    @Select("SELECT student_id FROM group_apply_member WHERE course_id = #{course_id}")
    List<String> findStudentIdByCourseId(Integer course_id);

    @Select("SELECT group_apply_id FROM group_apply_member WHERE student_id = #{student_id}")
    Integer findGroupApplyIdByStudentId(String student_id);

    @Select("SELECT group_role FROM group_apply_member WHERE student_id = #{student_id}")
    Integer findGroupRoleByStudentId(String student_id);

    @Select("SELECT gmt_create, gmt_modified, uid, group_apply_id, course_id, student_id, group_role " +
            "FROM group_apply_member " +
            "WHERE student_id = #{student_id}")
    @ResultMap("cn.miracle.octts.dao.GroupApplyMemberDao.GroupApplyMemberDetail")
    GroupApplyMember findGroupApplyMemberByStudentId(String student_id);

    @Insert("INSERT INTO group_apply_member(gmt_create, gmt_modified, uid, group_apply_id, course_id, student_id, group_role) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{group_apply_id}, #{course_id}, #{student_id}, #{group_role})")
    Integer insertGroupApplyMember(GroupApplyMember groupApplyMember);

}
