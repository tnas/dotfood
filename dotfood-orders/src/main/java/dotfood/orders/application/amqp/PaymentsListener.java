package dotfood.orders.application.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import dotfood.orders.application.config.AMQPConfigurator;
import dotfood.orders.application.dto.PaymentDto;

@Component
public class PaymentsListener {

	@RabbitListener(queues = AMQPConfigurator.ORDER_DETAILS_QUEUE)
	public void receiveMessages(@Payload PaymentDto payment) {
		
		if (payment.getOrderId().equals(0l)) {
			throw new RuntimeException(String.format("Invalid Order Id: %d", payment.getOrderId()));
		}
		
		var message = """
				Payment data: %s
				Order Id: %s
				Value $: %s
				Status: %s
				""".formatted(payment.getId(), payment.getOrderId(), payment.getValue(), payment.getStatus());
		
		System.out.println(message);
	}
}
