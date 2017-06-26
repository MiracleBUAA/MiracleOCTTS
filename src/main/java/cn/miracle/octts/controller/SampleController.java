package cn.miracle.octts.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by Tony on 2017/6/26.
 */
@Controller
@EnableAutoConfiguration
@SpringBootApplication
public class SampleController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello OCTTS!";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleController.class, args);
    }
}
