package com.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.crm.Repository")

public class CrmApplication {

	public static void main(String[] args) {SpringApplication.run(CrmApplication.class, args);
	}
}
