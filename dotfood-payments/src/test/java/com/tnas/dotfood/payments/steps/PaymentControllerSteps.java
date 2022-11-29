package com.tnas.dotfood.payments.steps;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.tnas.dotfood.payments.dto.PaymentDto;
import com.tnas.dotfood.payments.test.config.PaginatedResponse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@Testcontainers
public class PaymentControllerSteps {

	private String baseUrl;
	private ResponseEntity<PaginatedResponse<PaymentDto>> paginatedResponse;
	
	@Container
	private static final GenericContainer<?> rabbitMqContainer = new RabbitMQContainer("rabbitmq:3.11-management")
		.withUser("admin", "admin");
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Given("The microservice is available at {string}")
	public void the_microservice_is_available_at(String string) {
		this.baseUrl = string;
		rabbitMqContainer.start();
	}
	
	@When("I fetch payments at {string}")
	public void i_fetch_payments_at(String string) {
		this.paginatedResponse = this.restTemplate
				.exchange(this.baseUrl.concat("/payments"), HttpMethod.GET, null, 
						new ParameterizedTypeReference<PaginatedResponse<PaymentDto>>() {});
	}
	
	@Then("I should find a list of payments")
	public void i_should_find_a_list_of_payments() {
		assertEquals(HttpStatus.OK, this.paginatedResponse.getStatusCode());
		assertTrue(rabbitMqContainer.isRunning());
	}
}
