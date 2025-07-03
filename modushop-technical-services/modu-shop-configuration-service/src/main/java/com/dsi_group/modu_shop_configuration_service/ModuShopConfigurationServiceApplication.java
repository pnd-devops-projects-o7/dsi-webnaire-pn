package com.dsi_group.modu_shop_configuration_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ModuShopConfigurationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModuShopConfigurationServiceApplication.class, args);
	}
}
