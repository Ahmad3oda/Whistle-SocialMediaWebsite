package com.dailycodebuffer.usermanagementservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserManagementService {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementService.class, args);
	}

}
