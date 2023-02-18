package com.java.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.java.*")
public class SampleJavaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleJavaProjectApplication.class, args);
	}

}
