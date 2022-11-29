package com.tnas.dotfood.payments.steps;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.tnas.dotfood.payments.dto.PaymentDto;
import com.tnas.dotfood.payments.test.config.PaginatedResponse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PaymentControllerSteps {

	private String baseUrl;
	private ResponseEntity<PaginatedResponse<PaymentDto>> paginatedResponse;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Given("The microservice is available at {string}")
	public void the_microservice_is_available_at(String string) {
		this.baseUrl = string;
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
	}
}
