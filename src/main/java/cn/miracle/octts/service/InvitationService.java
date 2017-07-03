package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyDao;
import cn.miracle.octts.dao.InvitationDao;
import cn.miracle.octts.dao.StudentDao;
import cn.miracle.octts.entity.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Tony on 2017/7/3.
 */
@Service
public class InvitationService {

    @Autowired
    InvitationDao invitationDao;

    @Autowired
    GroupApplyDao groupApplyDao;

    @Autowired
    StudentDao studentDao;

    public List<String> findReceiverIdBySenderId(String sender_id) {
        return invitationDao.findReceiverIdBySenderId(sender_id);
    }

    public List<String> findSenderIdByReceiverId(String receiver_id) {
        return invitationDao.findSenderIdByReceiverId(receiver_id);
    }

    public HashMap<String, Object> invitation2Json(String sender_id) {
        HashMap<String, Object> data = new HashMap<>();

        data.put("group_apply_name", groupApplyDao.findGroupApplyNameByGroupApplyOwnerId(sender_id));
        data.put("sender_name", studentDao.findStudentNameById(sender_id));
        data.put("sender_id", sender_id);

        return data;
    }

    public List<HashMap<String, Object>> getInvitationList(String receiver_id) {
        List<HashMap<String, Object>> invitationList = new ArrayList<HashMap<String, Object>>();

        Iterator<String> senderIdIter = findSenderIdByReceiverId(receiver_id).iterator();
        while (senderIdIter.hasNext()) {
            HashMap<String, Object> invitation = invitation2Json(senderIdIter.next());
            invitationList.add(invitation);
        }
        return invitationList;
    }

    public Integer insertInvitation(Invitation invitation, String uid) {
        Date currentTime = new Date(System.currentTimeMillis());
        invitation.setCreatetime(currentTime);
        invitation.setUpdatetime(currentTime);
        invitation.setUid(uid);

        return invitationDao.insertInvitation(invitation);
    }

    public Integer deleteInvitationByReceiverId(String receiver_id) {
        return invitationDao.deleteInvitationByReceiverId(receiver_id);
    }

    public Integer deleteInvitationBySenderId(String sender_id) {
        return invitationDao.deleteInvitationBySenderId(sender_id);
    }

}
