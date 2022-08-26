package com.albo.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChallengeAlboApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeAlboApplication.class, args);
	}
}
