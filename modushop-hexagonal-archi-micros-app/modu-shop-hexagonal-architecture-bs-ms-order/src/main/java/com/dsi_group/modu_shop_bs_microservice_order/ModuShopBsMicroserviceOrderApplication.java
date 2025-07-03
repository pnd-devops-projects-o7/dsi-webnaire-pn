package com.dsi_group.modu_shop_bs_microservice_order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ModuShopBsMicroserviceOrderApplication {

	public static void main(String[] args) {
		SpringApplication
				.run(ModuShopBsMicroserviceOrderApplication.class,args);
	}

}
