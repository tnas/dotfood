package com.tnas.dotfood.payments.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnas.dotfood.payments.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
