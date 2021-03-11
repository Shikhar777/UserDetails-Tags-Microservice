package com.example.UserDetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients
@EnableJpaRepositories(basePackages = "com.example.UserDetails.repository")
public class UserDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDetailsApplication.class, args);
	}

}
