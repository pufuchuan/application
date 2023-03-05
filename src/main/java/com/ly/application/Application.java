package com.ly.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.ly.application.mapper"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("                                           .__  .__ \n" +
				"____________    ____   _____ _____  ___  __|  | |__|\n" +
				"\\_  __ \\__  \\  /    \\ /     \\\\__  \\ \\  \\/  /  | |  |\n" +
				" |  | \\// __ \\|   |  \\  Y Y  \\/ __ \\_>    <|  |_|  |\n" +
				" |__|  (____  /___|  /__|_|  (____  /__/\\_ \\____/__|\n" +
				"            \\/     \\/      \\/     \\/      \\/         \n" +
				"———————————————————————————————————————————————————");
	}

}
