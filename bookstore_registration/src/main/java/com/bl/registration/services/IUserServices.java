package com.bl.registration.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.bl.registration.dto.UserDTO;
import com.bl.registration.exception.LoginException;
import com.bl.registration.exception.UserNotFoundException;
import com.bl.registration.model.User;
import com.bl.registration.model.VerifyOTP;
import com.bl.registration.response.Response;

public interface IUserServices {

	User registerUser(UserDTO userDTO);

	List<User> getAllUsers(String loginToken)throws LoginException, UserNotFoundException;

	String loginUser(String email, String password) throws LoginException, UserNotFoundException;

	User getUser(String loginToken, long id)throws UserNotFoundException, LoginException;

	User deleteUser(String loginToken, long id)throws UserNotFoundException, LoginException;

	User editUser(String loginToken, long id, UserDTO user) throws UserNotFoundException, LoginException;

	Response resetPassword(String password, String token) throws UserNotFoundException;

	void forgotPassword(String email) throws UserNotFoundException;

	boolean verifyUser(String token) throws LoginException;

	void sendOTP(String token, String email) throws LoginException, UserNotFoundException;

	boolean verifyOTP(String token, VerifyOTP vrifyotp)throws LoginException, UserNotFoundException;

	boolean purchase(String token) throws LoginException;

	boolean expiryMail(String token, String email) throws LoginException, UserNotFoundException;

	void saveFromExcel(MultipartFile file) throws LoginException, UserNotFoundException;
}
