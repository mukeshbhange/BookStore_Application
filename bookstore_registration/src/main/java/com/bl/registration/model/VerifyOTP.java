package com.bl.registration.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mukesh_Bhange
 * @since 24/12/2021
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class VerifyOTP {
	
	private int otp;
	private String email;
}
