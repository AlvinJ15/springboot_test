package com.example.apipersons;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	
public class ApiPersonsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPersonsApplication.class, args);
	}
}
