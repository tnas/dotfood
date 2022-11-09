package com.tnas.dotfood.payments.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.tnas.dotfood.payments.model.Order;

@FeignClient("ms-orders")
public interface OrderClient {

	@PutMapping("/orders/{id}/paid")
	void updatePayment(@PathVariable Long id);
	
	@GetMapping("/orders/{id}")
	Order getOrderPayment(@PathVariable Long id);
}
