package com.bl.registration.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bl.registration.dto.UserDTO;
import com.bl.registration.exception.LoginException;
import com.bl.registration.exception.UserNotFoundException;
import com.bl.registration.model.User;
import com.bl.registration.model.VerifyOTP;
import com.bl.registration.response.Response;
import com.bl.registration.services.IUserServices;
import com.bl.registration.util.ExcelHelper;
import com.bl.registration.util.TokenUtil;

/**
 * @author Mukesh_Bhange
 * 
 * @version 1.0
 * @since 27/12/2021
 *
 */


@RestController
@RequestMapping("/admin")
public class UserController {
	
	@Autowired
	private IUserServices userServices;
	@Autowired
	private TokenUtil tokenutil;
	
	/*Registering user for Book Store*/
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody UserDTO userDTO){
		User user = userServices.registerUser(userDTO);
		Response response = new Response("Token: "+ tokenutil.createToken(user.getId()),(long)200,user);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	/*getting all users from Book Store*/
	@GetMapping("/allUsers")
	public ResponseEntity<Response> getAllUsers(@RequestHeader String loginToken) throws LoginException, UserNotFoundException{
		List<User> users = userServices.getAllUsers(loginToken);
		Response response = new Response("Get all users successfully",(long)200, users);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	/*Login user by using email and password */
	@GetMapping("/login")
	public ResponseEntity<Response> checkUser(@RequestHeader String email, @RequestHeader String password) throws LoginException, UserNotFoundException{
		String isLogin = userServices.loginUser(email, password);
		Response response = new Response("Login successfully",(long)200, isLogin);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	
	/*getting  user from Book Store by userId*/
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getUser(@RequestHeader String loginToken,@PathVariable long id) throws UserNotFoundException, LoginException {
		User user = userServices.getUser(loginToken, id);
		Response response = new Response("Get user successfully ",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/*Deleting  user from Book Store by userId*/
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<Response> deleteUser(@RequestHeader String loginToken, @PathVariable long id) throws UserNotFoundException, LoginException {
		User user = userServices.deleteUser(loginToken,id);
		Response response = new Response("Deleted user successfully",(long)200, user);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/*updating  user from Book Store by userId*/
	@PutMapping("/edit/{id}")
	public ResponseEntity<Response> editUser(@RequestHeader String loginToken, @PathVariable long id,  @RequestBody UserDTO user) throws UserNotFoundException, LoginException {
		User updatedUser = userServices.editUser(loginToken, id, user);
		Response response = new Response("Updated user successfully",(long)200, updatedUser);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	/*sending forgot email link  to user from Book Store by email*/
	@GetMapping("/forgot")
	ResponseEntity<String> forgotPass(@RequestParam String email) throws UserNotFoundException{
		userServices.forgotPassword(email);
		
		return new ResponseEntity<String>("Reset Link sent",HttpStatus.OK);
	}
	
	
	/*resetting password*/
	@GetMapping("/reset/{token}")
	ResponseEntity<Response> resetpass( @RequestParam String password, @PathVariable String token) throws UserNotFoundException {
		Response res = userServices.resetPassword(password, token);
		return new ResponseEntity<Response>(res, HttpStatus.OK);
	}
	
	/* Checking if user is present in UserDatabase*/
	@GetMapping("/verify")
	ResponseEntity<Boolean> verifyUser(@RequestParam String token)throws LoginException{
		boolean verifiedUser = userServices.verifyUser(token);
		return new ResponseEntity<Boolean>(verifiedUser,HttpStatus.OK);
	}
	
	
	/* Sending 	OTP to verify User*/
	@GetMapping("/sendotp")
	ResponseEntity<String> sendOtp(@RequestHeader String token,@RequestParam String email)throws LoginException, UserNotFoundException{
		userServices.sendOTP(token,email);
		return new ResponseEntity<String>("OTP has been sent",HttpStatus.OK);
	}
	
	
	/*Verifying OTP sent*/
	@GetMapping("/verifydotp")
	ResponseEntity<String> verifyOtp(@RequestHeader String token,@RequestBody VerifyOTP vrifyotp)throws LoginException, UserNotFoundException{
		if(userServices.verifyOTP(token,vrifyotp)) {
			return new ResponseEntity<String>("OTP has been Verified",HttpStatus.OK);
		}else {
			throw new LoginException("Wrong OTP");
		}
		
	}
	
	
	/* Buying subscription for BookStore */
	@GetMapping("/purchase")
	ResponseEntity<String> purchase(@RequestHeader String token)throws LoginException, UserNotFoundException{
		if(userServices.purchase(token)) {
			return new ResponseEntity<String>("Book Store Subscription has been purchased",HttpStatus.OK);
		}else {
			throw new LoginException("Wrong OTP");
		}
		
	}
	
	
	/* Expire mail sending to User whose subscription is ending*/
	@GetMapping("/expirymail")
	ResponseEntity<String> expiryMail(@RequestHeader String token,@RequestParam String email)throws LoginException, UserNotFoundException{
		if(userServices.expiryMail(token,email)) {
			return new ResponseEntity<String>("Expiration mail has been sent",HttpStatus.OK);
		}else {
			throw new LoginException("Wrong OTP");
		}
		
	}
	
	
	@PostMapping("/uploadexcel")
	public ResponseEntity<?> uploadExcel(@RequestParam("file") MultipartFile file)  throws LoginException, UserNotFoundException{
		if(ExcelHelper.checkExcelFormat(file)) {
			userServices.saveFromExcel(file);
			return ResponseEntity.ok("Excel file Uploaded and file infoSaved");
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel File only");
		}
		
	}

}
