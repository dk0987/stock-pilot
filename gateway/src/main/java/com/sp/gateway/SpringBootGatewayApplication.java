package com.sp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.simple.SimpleDiscoveryClientAutoConfiguration;

@SpringBootApplication
public class SpringBootGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGatewayApplication.class, args);
	}

}
