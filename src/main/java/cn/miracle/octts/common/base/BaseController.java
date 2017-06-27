package cn.miracle.octts.common.base;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by hf on 2017/6/27.
 */
public class BaseController {

    protected static BaseResponse setParamError() {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(1);
        response.setErrorMsg("参数错误");
        response.setData(new HashMap<>());
        return response;
    }

    public static BaseResponse setCorrectResponse(HashMap<String, Object> data) {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        response.setData(data);
        return response;
    }

    public static BaseResponse setFileUploadError() {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(3);
        response.setErrorMsg("文件为空");
        response.setData(new HashMap<>());
        return response;
    }
}
