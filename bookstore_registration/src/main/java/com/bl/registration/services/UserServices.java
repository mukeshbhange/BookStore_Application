package com.bl.registration.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bl.registration.dto.UserDTO;
import com.bl.registration.exception.LoginException;
import com.bl.registration.exception.UserNotFoundException;
import com.bl.registration.model.User;
import com.bl.registration.model.VerifyOTP;
import com.bl.registration.repository.IUserRepository;
import com.bl.registration.response.Response;
import com.bl.registration.util.MailServices;
import com.bl.registration.util.TokenUtil;


@Service
public class UserServices implements IUserServices {
	
	@Autowired
	private TokenUtil tokenutil;
	
	@Autowired
	private IUserRepository userRepo;
	
	@Autowired
	private MailServices mailServices;

	@Override
	public User registerUser(UserDTO userDTO) {
		User user = new User(userDTO);
		userRepo.save(user);
		return user;
	}

	@Override
	public List<User> getAllUsers(String loginToken)throws LoginException, UserNotFoundException {
		if(verifyUser(loginToken)) {
			List<User> userData = new ArrayList<>();
			userRepo.findAll().forEach(userData::add);
			if(userData.isEmpty()) {
				throw new UserNotFoundException("No Data Present in Database,First Add Data");
			}else {
				return userData;
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public String loginUser(String email, String password) throws LoginException, UserNotFoundException {
		User user = userRepo.findByEmail(email);
		if(user != null) {
			if(user.getPassword().equals(password)){
				return tokenutil.createToken(user.getId());
			}else {
				throw new LoginException("Wrong Password!");
			}
			
		}else {
			throw new UserNotFoundException("User of this email does not exist");
		}
		
	}

	@Override
	public User getUser(String loginToken, long id)  throws UserNotFoundException, LoginException {
		if(verifyUser(loginToken)) {
			if(userRepo.findById(id).isEmpty()) {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}else {
				return userRepo.findById(id).get();

			}	
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public User deleteUser(String loginToken, long id) throws UserNotFoundException, LoginException {
		if(verifyUser(loginToken)) {
			if(!userRepo.findById(id).isEmpty()) {
				User deleted = getUser(loginToken,id);
				userRepo.deleteById(id);
				return deleted;
			}else {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public User editUser(String loginToken, long id, UserDTO user)  throws UserNotFoundException, LoginException {
		if(verifyUser(loginToken)) {
			User found_user = this.getUser( loginToken,id);

			if(found_user != null) {
				
				found_user.setFirstName(user.getFirstName());
				found_user.setLastName(user.getLastName());
				found_user.setEmail(user.getEmail());
				found_user.setPassword(user.getPassword());
				found_user.setKyc(user.getKyc());
				found_user.setUpdateDate(LocalDateTime.now());
				userRepo.save(found_user);

				return found_user;
			}else {
				throw new UserNotFoundException(" "+id+" Id is not Present");
			}
		}else {
			throw new LoginException("Access Denied...!");
		}
	}

	@Override
	public Response resetPassword(String password, String token) throws UserNotFoundException {
		long id = tokenutil.decodeToken(token);
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			user.get().setPassword(password);
			user.get().setUpdateDate(LocalDateTime.now());
			return new Response("New Password Saved SuccessFully....!",(long)200);
			
		}else {
			throw new UserNotFoundException("This User is not Present at this Database");
		}	
	}

	@Override
	public void forgotPassword(String email) throws UserNotFoundException {
		if(userRepo.findByEmail(email) != null) {
			MailServices.send(email,mailServices.getLink("http://localhost:8991/reset/",userRepo.findByEmail(email).getId()), "Reset Link Sent to you.");
		}else {
			throw new UserNotFoundException("this email not present in this database");
		}
	}

	@Override
	public boolean verifyUser(String token) throws LoginException{
		Optional<User> isPresent = userRepo.findById(tokenutil.decodeToken(token));
		if(isPresent.isPresent()) {
			isPresent.get().setVerify(true);
			userRepo.save(isPresent.get());
			return true;
		}else {
			throw new LoginException("Please log in first and use token...!");
		}
		
	}

	@Override
	public void sendOTP(String token, String email) throws LoginException, UserNotFoundException {
		if(verifyUser(token)) {
			if(userRepo.findByEmail(email) != null) {
				int otp = mailServices.createOTP();
				MailServices.send(email, ""+otp,"OTP has been sent");
				User user = userRepo.findByEmail(email);
				user.setOtp(otp);
				userRepo.save(user);
			}else {
				throw new UserNotFoundException("this email not present in this database");
			}
		}
	}

	@Override
	public boolean verifyOTP(String token,VerifyOTP varifyotpBody) throws LoginException, UserNotFoundException {
		if(verifyUser(token)) {
			if(userRepo.findByEmail(varifyotpBody.getEmail()) != null) {
				if(varifyotpBody.getOtp() == userRepo.findByEmail(varifyotpBody.getEmail()).getOtp()) {
					return true;
				}else {
					throw new LoginException("this email not present in this database");
				}	
			}else {
				throw new UserNotFoundException("this email not present in this database");
			}
		}else {
			throw new LoginException("this email not present in this database");
		}	
		
	}

}