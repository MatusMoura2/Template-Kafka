package com.mouraforte.mfproducer.config;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaAdminConfig {

	
	public final KafkaProperties kafkaProperties;

	@Bean
	KafkaAdmin kafkaAdmin() {
		var configs = new HashMap<String, Object>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG,15000);
		return new KafkaAdmin(configs);
	}

    @Bean
    KafkaAdmin.NewTopics newTopics() {
    	System.out.println("Tentando criar um mew topc!");
		return new KafkaAdmin.NewTopics(TopicBuilder.name("str-topic").partitions(2).replicas(1).build());
	}
}
