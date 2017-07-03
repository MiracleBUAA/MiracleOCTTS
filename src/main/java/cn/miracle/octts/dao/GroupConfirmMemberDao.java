package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.GroupConfirmMember;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tony on 2017/7/2.
 */
public interface GroupConfirmMemberDao extends BaseMapper<GroupConfirmMember> {

    @Select("SELECT student_id FROM group_confirm_member WHERE group_id = #{group_id}")
    List<String> findStudentIdByGroupId(Integer group_id);

    @Insert("INSERT INTO group_confirm_member(gmt_create, gmt_modified, uid, group_id, course_id, student_id, group_role) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{group_id}, #{course_id}, #{student_id}, #{group_role})")
    Integer insertGroupConfirmMember(GroupConfirmMember groupConfirmMember);

    @Select("SELECT group_id FROM group_confirm_member WHERE student_id = #{student_id}")
    Integer findGroupIdByStudentId(String student_id);

    @Select("SELECT student_id FROM group_confirm_member WHERE course_id = #{course_id}")
    List<String> findStudentIdByCourseId(Integer course_id);

    @Select("SELECT gmt_create, gmt_modified, uid, group_id, course_id, student_id, group_role " +
            "FROM group_confirm_member " +
            "ORDER BY group_id")
    @ResultMap("cn.miracle.octts.dao.GroupConfirmMemberDao.GroupConfirmMemberDetail")
    List<GroupConfirmMember> findAllGroupConfirmMember();

}
