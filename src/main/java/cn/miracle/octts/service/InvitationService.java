package cn.miracle.octts.service;

import cn.miracle.octts.dao.GroupApplyDao;
import cn.miracle.octts.dao.InvitationDao;
import cn.miracle.octts.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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

}
