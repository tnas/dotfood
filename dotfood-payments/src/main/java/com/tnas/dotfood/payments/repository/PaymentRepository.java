package com.tnas.dotfood.payments.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.dotfood.payments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
