package com.bl.registration.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bl.registration.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String firstName;
	private String lastName;
	private String kyc;
	private Date dob;
	
	
	private String email;
	private String password;
	
	private boolean verify;
	private int otp;
	private LocalDateTime purchaseDate;
	private LocalDateTime expiryDate;
	 
	
	private LocalDateTime registerDate; 
	private LocalDateTime updateDate;
	
	public User(UserDTO userDTO) {
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.kyc = userDTO.getKyc();
		this.dob = userDTO.getDob();
		this.email = userDTO.getEmail();
		this.password = userDTO.getPassword();
		this.verify = false;
		this.registerDate = LocalDateTime.now();
	}
}
