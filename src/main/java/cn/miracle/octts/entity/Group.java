package cn.miracle.octts.entity;


import cn.miracle.octts.common.base.BaseEntity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Tony on 2017/6/27.
 */
public class Group extends BaseEntity {
    private String group_id;
    private String group_name;
    private String group_owner_id;
    private String group_score;
    private String group_status;

    public Group() {
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_owner_id() {
        return group_owner_id;
    }

    public void setGroup_owner_id(String group_owner_id) {
        this.group_owner_id = group_owner_id;
    }

    public String getGroup_score() {
        return group_score;
    }

    public void setGroup_score(String group_score) {
        this.group_score = group_score;
    }

    public String getGroup_status() {
        return group_status;
    }

    public void setGroup_status(String group_status) {
        this.group_status = group_status;
    }
}
