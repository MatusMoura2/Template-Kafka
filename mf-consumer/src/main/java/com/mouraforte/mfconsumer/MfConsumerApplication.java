package com.mouraforte.mfconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class MfConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfConsumerApplication.class, args);
	}

}
