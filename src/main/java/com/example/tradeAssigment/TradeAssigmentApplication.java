package com.example.tradeAssigment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeAssigmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TradeAssigmentApplication.class, args);
	}

}
