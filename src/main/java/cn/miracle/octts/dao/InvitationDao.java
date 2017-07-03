package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Invitation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Tony on 2017/7/3.
 */
public interface InvitationDao extends BaseMapper<Invitation> {

    @Select("SELECT receiver_id FROM invitation WHERE sender_id = #{sender_id}")
    List<String> findReceiverIdBySenderId(String sender_id);

    @Select("SELECT sender_id FROM invitation WHERE receiver_id = #{receiver_id}")
    List<String> findSenderIdByReceiverId(String receiver_id);

    @Insert("INSERT INTO invitation(gmt_create, gmt_modified, uid, sender_id, receiver_id) " +
            "VALUES(#{createtime}, #{updatetime}, #{uid}, #{sender_id}, #{receiver_id})")
    Integer insertInvitation(Invitation invitation);

    @Delete("DELETE FROM invitation WHERE receiver_id = #{receiver_id}")
    Integer deleteInvitationByReceiverId(String receiver_id);

    @Delete("DELETE FROM invitation WHERE sender_id = #{sender_id}")
    Integer deleteInvitationBySenderId(String sender_id);

    @Delete("DELETE FROM invitation WHERE sender_id = #{sender_id} AND receiver_id = #{receiver_id}")
    Integer deleteInvitationBySenderIdAndReceiverId(@Param(value = "sender_id") String sender_id,
                                                    @Param(value = "receiver_id") String receiver_id);

}
