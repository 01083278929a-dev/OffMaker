package com.nurse.schedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.nurse.schedule.mapper")
public class NurseScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(NurseScheduleApplication.class, args);
    }
}
