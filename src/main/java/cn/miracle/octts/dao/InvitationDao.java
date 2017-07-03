package cn.miracle.octts.dao;

import cn.miracle.octts.common.base.BaseMapper;
import cn.miracle.octts.entity.Invitation;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.ResultMap;
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

}
