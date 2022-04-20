package com.example.handy;

import com.example.handy.controllers.UserInfoController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class HandyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandyApplication.class, args);
	}

}
