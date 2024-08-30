package com.mouraforte.paymentservice.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mouraforte.paymentservice.model.Payment;

public interface PaymentResoutrce {
	
	@PostMapping
	ResponseEntity<Payment> payment(@RequestBody Payment payment);

}
