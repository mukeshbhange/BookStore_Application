package com.bl.registration.dto;

import java.time.LocalDate;
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
public class UserDTO {
	
	private String firstName;
	private String lastName;
	private String kyc;
	private LocalDate dob;
	private String email;
	private String password;
}
