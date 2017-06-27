package cn.miracle.octts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableAutoConfiguration
@EnableRedisHttpSession
@MapperScan("cn.miracle.octts.dao")
public class OcttsApplication {
	public static void main(String[] args) {
		SpringApplication.run(OcttsApplication.class, args);
	}
}
