package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MailBulkKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MailBulkKafkaApplication.class, args);
	}

}
