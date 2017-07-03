package cn.miracle.octts.entity;

import cn.miracle.octts.common.base.BaseEntity;

/**
 * Created by Tony on 2017/7/1.
 */
public class Invitation extends BaseEntity {
    private String sender_id;
    private String receiver_id;

    public Invitation() {
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }
}
