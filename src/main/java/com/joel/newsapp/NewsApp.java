package com.joel.newsapp;

import com.joel.newsapp.utils.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
public class NewsApp {

	public static void main(String[] args) {
		SpringApplication.run(NewsApp.class, args);
	}

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public CommandLineRunner createPasswordCommand(){
		return args -> {
			System.out.println(passwordEncoder.encode("pass123"));
			System.out.println(UUID.randomUUID().toString());
		};
	}

}
