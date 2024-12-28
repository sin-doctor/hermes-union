package com.hermes.hermes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.hermes.hermes.mapper")
public class HermesApplication {
	public static void main(String[] args) {
		SpringApplication.run(HermesApplication.class, args);
	}
}