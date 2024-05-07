package com.dailycodebuffer.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableConfigServer
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:8088"})

public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
