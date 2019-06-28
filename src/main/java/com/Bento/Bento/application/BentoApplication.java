package com.Bento.Bento.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class BentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BentoApplication.class, args);
	}

}
