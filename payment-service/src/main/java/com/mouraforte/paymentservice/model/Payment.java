package com.mouraforte.paymentservice.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class Payment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long idUser;
	private Long idProducer;
	private String cardNumber;
	

}
