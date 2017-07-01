package cn.miracle.octts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by hf on 2017/7/1.
 */
@Service
public class FileService {

//    @Autowired
//    private HttpHeaders httpHeaders;

    public HttpHeaders getFileDownloadHeaders(String file_title) throws UnsupportedEncodingException {
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
