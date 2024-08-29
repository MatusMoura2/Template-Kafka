package com.mouraforte.mfconsumer.listeners;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.mouraforte.mfconsumer.custom.StringConsumerCustomListener;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class StringConsumerListener {

	@SneakyThrows
	@StringConsumerCustomListener(groupId = "group-1")
	public void create(String message){
		log.info("Create ::: Receive message {}",message);
		throw new IllegalArgumentException("EXCEPTION...");
	}
	@StringConsumerCustomListener(groupId = "group-1")
	public void log(String message) {
		log.info("LOG ::: Receive message {}",message);
	}
	
	@KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
	public void history(String message) {
		log.info("History ::: Receive message {}",message);
	}
}
