package dotfood.orders.application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
	public static final String DEAD_LETTER_EXCHANGE = "dotfood.payments.dlx";
	public static final String ORDER_DETAILS_QUEUE = "dotfood.order-details";
	public static final String ORDER_DETAILS_DLQ = "dotfood.order-details-dlq";
	
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
	
	@Bean
	public Queue orderDetailsQueue() {
		return QueueBuilder
				.nonDurable(ORDER_DETAILS_QUEUE)
				.deadLetterExchange(DEAD_LETTER_EXCHANGE)
				.build();
	}
	
	@Bean
	public Queue orderDetailsDlq() {
		return QueueBuilder.nonDurable(ORDER_DETAILS_DLQ).build();
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		return ExchangeBuilder.fanoutExchange(FANOUT_EXCHANGE).build();
	}
	
	@Bean
	public FanoutExchange deadLetterExchange() {
		return ExchangeBuilder.fanoutExchange(DEAD_LETTER_EXCHANGE).build();
	}
	
	@Bean
	public Binding bindOrderPayment() {
		return BindingBuilder.bind(this.orderDetailsQueue())
				.to(fanoutExchange());
	}
	
	@Bean
	public Binding bindDlxOrderPayment() {
		return BindingBuilder.bind(this.orderDetailsDlq())
				.to(deadLetterExchange());
	}
	
	@Bean
	public RabbitAdmin rabbitAdmin(ConnectionFactory con) {
		return new RabbitAdmin(con);
	}
	
	@Bean
	public ApplicationListener<ApplicationReadyEvent> initAdmin(RabbitAdmin admin) {
		return event -> admin.initialize();
	}
}
