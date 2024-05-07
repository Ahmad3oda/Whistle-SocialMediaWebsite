package com.dailycodebuffer.friendsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:8088"})

public class FriendsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FriendsServiceApplication.class, args);
	}

}
