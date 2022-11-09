package com.tnas.dotfood.payments.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tnas.dotfood.payments.dto.PaymentoDto;
import com.tnas.dotfood.payments.http.OrderClient;
import com.tnas.dotfood.payments.model.Payment;
import com.tnas.dotfood.payments.model.Status;
import com.tnas.dotfood.payments.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrderClient orderClient;
	
	public Page<PaymentoDto> getAll(Pageable page) {
		return this.repository
				.findAll(page)
				.map(p -> this.modelMapper.map(p, PaymentoDto.class));
	}
	
	public PaymentoDto getById(Long id) {
		
		var payment = this.repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		
		var dto = this.modelMapper.map(payment, PaymentoDto.class);
		dto.setItems(this.orderClient.getOrderPayment(payment.getOrderId()).getItems());
		
		return dto;
	}
	
	public PaymentoDto createPayment(PaymentoDto dto) {
		
		var payment = this.modelMapper.map(dto, Payment.class);
		payment.setStatus(Status.CREATED);
		this.repository.save(payment);
		
		return this.modelMapper.map(payment, PaymentoDto.class);
	}
	
	public PaymentoDto updatePayment(Long id, PaymentoDto dto) {
		
		var payment = this.modelMapper.map(dto, Payment.class);
		payment.setId(id);
		payment = this.repository.save(payment);
		
		return this.modelMapper.map(payment, PaymentoDto.class);
	}
	
	public void deletePayment(Long id) {
		this.repository.deleteById(id);
	}
	
	public void confirmPayment(Long id) {
		var payment = this.updateStatus(id, Status.CONFIRMED);
		this.orderClient.updatePayment(payment.getOrderId());
	}
	
	public Payment updateStatus(Long id, Status status) {
		
		var payment = this.repository.findById(id);
		
		if (!payment.isPresent()) {
			throw new EntityNotFoundException();
		}
		
		payment.get().setStatus(status);
		this.repository.save(payment.get());
		
		return payment.get();
	}
}
