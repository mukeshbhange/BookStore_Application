package com.bl.orderbook.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	private long id;
	private String firstName;
	private String lastName;
	private String kyc;
	private LocalDate dob;
	private String email;
	private String password;
	private boolean verify;
	private int otp;
	private LocalDateTime purchaseDate;
	private LocalDateTime expiryDate;
	private LocalDateTime registerDate; 
	private LocalDateTime updateDate;
}
