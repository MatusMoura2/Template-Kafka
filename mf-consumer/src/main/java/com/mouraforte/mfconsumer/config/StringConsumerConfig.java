package com.mouraforte.mfconsumer.config;


import java.util.HashMap;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Configuration
public class StringConsumerConfig {

	private final KafkaProperties kafkaProperties;

	@Bean
	ConsumerFactory<String, String> consumerFactory() {
		var configs = new HashMap<String, Object>();
		configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return new DefaultKafkaConsumerFactory<>(configs);
	}

	@Bean
	ConcurrentKafkaListenerContainerFactory<String, String> stringContainerFactory(
			ConsumerFactory<String, String> consumerFactory) {
		var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> validMessageContainerFactory(
                ConsumerFactory<String, String> consumerFactory
    ) {
	        var factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
	        factory.setConsumerFactory(consumerFactory);
	        factory.setRecordInterceptor(validMessage());
	        return factory;
	    }

	    private RecordInterceptor<String, String> validMessage() {
	        return new RecordInterceptor<String, String>() {
				
				@Override
				public ConsumerRecord<String, String> intercept(ConsumerRecord<String, String> record,
						Consumer<String, String> consumer) {
						if(record.value().contains("Teste")) {
							log.info("Possui a palavra test");
						}
					return record;
				}
			};
	    }
}
