package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.users.repository")
public class AuthApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(AuthApp.class, args);

	}

}
