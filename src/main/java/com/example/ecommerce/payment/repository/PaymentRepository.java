package com.example.ecommerce.payment.repository;

import com.example.ecommerce.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}