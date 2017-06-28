package cn.miracle.octts.common.base;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by hf on 2017/6/27.
 */
public class BaseResponse {

    private int ErrorNo;

    private String ErrorMsg;

//    private String uid;
//
//    private Integer urank;

    private HashMap<String, Object> data;

    public BaseResponse() {
    }

    public int getErrorNo() {
        return ErrorNo;
    }

    public void setErrorNo(int errorNo) {
        ErrorNo = errorNo;
    }

    public String getErrorMsg() {
        return ErrorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        ErrorMsg = errorMsg;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

//    public String getUid() {
//        return uid;
//    }
//
//    public void setUid(String uid) {
//        this.uid = uid;
//    }
//
//    public Integer getUrank() {
//        return urank;
//    }
//
//    public void setUrank(Integer urank) {
//        this.urank = urank;
//    }
}
