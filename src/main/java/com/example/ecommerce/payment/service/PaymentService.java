package com.example.ecommerce.payment.service;

import com.example.ecommerce.payment.dto.PaymentRequest;
import com.example.ecommerce.payment.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
}