package com.qfjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan(basePackages = {"com.qfjy.mapper"})
@EnableScheduling

public class DuduMeetingWeixinApplication {

    public static void main(String[] args) {
        SpringApplication.run(DuduMeetingWeixinApplication.class, args);
    }

}
