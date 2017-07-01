package cn.miracle.octts.common.base;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    protected static BaseResponse setCorrectResponse(HashMap<String, Object> data) {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        response.setData(data);
        return response;
    }

    protected static BaseResponse setFileUploadError() {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(4);
        response.setErrorMsg("文件为空");
        response.setData(new HashMap<>());
        return response;


    }

    protected static BaseResponse setCorrectUpdate() {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        return response;
    }

    protected static BaseResponse setCorrectInsert() {
        BaseResponse response = new BaseResponse();
        response.setErrorNo(0);
        response.setErrorMsg("OK");
        return response;
    }

    protected static HttpHeaders getFileDownloadHeaders(String file_title) throws UnsupportedEncodingException {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("charset", "utf-8");
        String file_name = URLEncoder.encode(file_title, "UTF-8");
        headers.add("Content-Disposition", "attachment;filename=\"" + file_name + "\"");

        return headers;
    }
}
