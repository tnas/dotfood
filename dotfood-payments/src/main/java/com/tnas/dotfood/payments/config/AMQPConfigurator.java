package com.tnas.dotfood.payments.config;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfigurator {

	public static final String FANOUT_EXCHANGE = "dotfood.payments.ex";

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(FANOUT_EXCHANGE);
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory con) {
		return new RabbitAdmin(con);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin admin) {
		return event -> admin.initialize();
	}
	
	@Bean
	public Jackson2JsonMessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory con, Jackson2JsonMessageConverter converter) {
		var rabbitTemplate = new RabbitTemplate(con);
		rabbitTemplate.setMessageConverter(converter);
		return rabbitTemplate;
	}
}
