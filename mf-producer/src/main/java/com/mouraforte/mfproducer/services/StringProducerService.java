package com.mouraforte.mfproducer.services;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		kafkaTemplate.send("str-topic", message).thenAccept(result -> {
			if (result != null) {
				log.info("Sent message successfully: {}", message);
				log.info("Partitian " + result.getRecordMetadata().partition() + "\n" + "Offset "
						+ result.getRecordMetadata().offset());

			}
		}).exceptionally(ex -> {
			log.error("Error sending message: {}", message, ex);
			return null;
		});
	}

}
