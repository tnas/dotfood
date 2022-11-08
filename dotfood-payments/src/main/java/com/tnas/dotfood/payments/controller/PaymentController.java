package com.tnas.dotfood.payments.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.tnas.dotfood.payments.dto.PaymentoDto;
import com.tnas.dotfood.payments.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService service;
	
	@GetMapping
	public Page<PaymentoDto> list(@PageableDefault(size = 10) Pageable page) {
		return this.service.getAll(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentoDto> detail(@PathVariable @NotNull Long id) {
		return ResponseEntity.ok(this.service.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<PaymentoDto> create(
			@RequestBody @Valid PaymentoDto dto, UriComponentsBuilder uriBuilder) {
		
		var payment = this.service.createPayment(dto);
		var address = uriBuilder.path("/payments/{id}").buildAndExpand(payment.getId()).toUri(); 
				
		return ResponseEntity.created(address).body(payment);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PaymentoDto> update(
			@PathVariable @NotNull Long id, @RequestBody @Valid PaymentoDto dto) {
		return ResponseEntity.ok(this.service.updatePayment(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<PaymentoDto> delete(@PathVariable @NotNull Long id) {
		this.service.deletePayment(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/confirm")
	public void confirmPayment(@PathVariable Long id) {
		this.service.confirmPayment(id);
	}
	
}
