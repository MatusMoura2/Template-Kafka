package com.mouraforte.Json_consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.mouraforte.Json_consumer.model.Payment;

import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import static java.lang.Thread.sleep;

@ToString
@Log4j2
@Component
public class JsonListener {

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "jsonContainerFactory")
	public void antiFraud(@Payload Payment payment) {
		log.info("Recebi o meu pagamento... {}", payment.toString());
		sleep(2000);

		log.info("Validando fraude...");
		sleep(2000);

		log.info("Compra aprovada...");
		sleep(3000);
	}

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "jsonContainerFactory")
	public void pdfGenerator(@Payload Payment payment) {
		sleep(3000);
		log.info("Gerando PDF do produto de id {}...", payment.getId());
		sleep(3000);
	}

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "jsonContainerFactory")
	public void sendEmail() {
		sleep(3000);
		log.info("Enviando Email de confirmação...");

	}
}
