package com.tnas.dotfood.payments.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfigurator {

	private static final String PAYMENT_FINISHED_Q = "payment.finished";

	@Bean
	public Queue createQueue() {
		return QueueBuilder.nonDurable(PAYMENT_FINISHED_Q).build();
	}
	
	@Bean
	public RabbitAdmin createAdmin(ConnectionFactory con) {
		return new RabbitAdmin(con);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin admin) {
		return event -> admin.initialize();
	}
}
