package cn.miracle.octts.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Tony on 2017/6/27.
 */
@RestController
public class LoginController {
    private static final String template = "{\n" +
            "    \"errno\": 0,\n" +
            "    \"errmsg\": \"ok\",\n" +
            "    \"time\": 1498464233,\n" +
            "    \"data\": {\n" +
            "        \"uid\": 142123333,\n" +
            "        \"desc\": \"success\"\n" +
            "    }\n" +
            "}";
    private final AtomicLong counter = new AtomicLong();

    //sample:
    //http://localhost:8080/login?uid=14212333&password=123456
    //return { "errno": 0, "errmsg": "ok", "time": 1498464233, "data": { "uid": 142123333, "desc": "success" } }
    @RequestMapping("/login")
    public String login(@RequestParam(value = "uid", required = true) String uid,
                        @RequestParam(value = "password", required = true) String password) {
        if (uid.equals("14212333") && (password.equals("123456")))
            return template;
        else
            return "error.";
    }
}
