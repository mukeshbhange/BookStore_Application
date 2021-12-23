package com.bl.registration.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private String firstName;
	private String lastName;
	private String kyc;
	private LocalDate dob;
	private String email;
	private String password;
	
	/* private LocalDateTime purchaseDate;
	 * private LocalDateTime expiryDate;
	 */
}
