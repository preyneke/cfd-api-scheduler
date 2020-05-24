package com.gt247.cfdapischeduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CfdApiSchedulerApplication {

	public static void main(String[] args) {

		SpringApplication.run(CfdApiSchedulerApplication.class, args);

	}

}
