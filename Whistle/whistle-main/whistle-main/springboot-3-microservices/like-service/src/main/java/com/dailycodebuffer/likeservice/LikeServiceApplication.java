package com.dailycodebuffer.likeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LikeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LikeServiceApplication.class, args);
	}

}
