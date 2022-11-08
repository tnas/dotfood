package com.tnas.dotfood.payments.service;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tnas.dotfood.payments.dto.PaymentoDto;
import com.tnas.dotfood.payments.model.Payment;
import com.tnas.dotfood.payments.model.Status;
import com.tnas.dotfood.payments.repositories.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Page<PaymentoDto> getAll(Pageable page) {
		return this.repository
				.findAll(page)
				.map(p -> this.modelMapper.map(p, PaymentoDto.class));
	}
	
	public PaymentoDto getById(Long id) {
		
		var payment = this.repository.findById(id)
				.orElseThrow(EntityNotFoundException::new);
		
		return this.modelMapper.map(payment, PaymentoDto.class);
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
}
