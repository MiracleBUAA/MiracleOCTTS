package cn.miracle.octts.common.base;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * Created by hf on 2017/6/27.
 */
public class BaseResponse {

    private int ErrorNo;

    private String ErrorMsg;

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

}
