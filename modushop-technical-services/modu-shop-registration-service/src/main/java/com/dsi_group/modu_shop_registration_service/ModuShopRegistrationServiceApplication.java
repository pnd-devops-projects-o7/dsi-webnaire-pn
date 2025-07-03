package com.dsi_group.modu_shop_registration_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ModuShopRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuShopRegistrationServiceApplication.class, args);
	}
}
