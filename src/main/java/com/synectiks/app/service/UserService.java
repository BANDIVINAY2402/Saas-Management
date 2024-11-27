package com.synectiks.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synectiks.app.entity.Users;
import com.synectiks.app.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userrepo;
	public String Signup(Users signupdetails) {
		System.out.println(userrepo.findByEmail(signupdetails.getEmail()));
		if(userrepo.findByEmail(signupdetails.getEmail())!=null)
		{
			return "user already exist";
		}
		else {
			Users signup=new Users();
			signup.setEmail(signupdetails.getEmail());
			signup.setFirstname(signupdetails.getFirstname());
			signup.setLastname(signupdetails.getLastname());
			signup.setPassword(signupdetails.getPassword());
			System.out.println(signup);
			userrepo.save(signup);
			
			return "user registered successfully";
		}
	}

	public void sendOtpToUser(String email) {
		// TODO Auto-generated method stub
		
	}

	public boolean validateOtp(String email, String otp) {
		// TODO Auto-generated method stub
		return false;
	}

	public void resetPassword(String email, String otp, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	public boolean signIn(String email, String password) {
		 Optional<Users> signupOpt = Optional.ofNullable(userrepo.findByEmail(email));

		    if (signupOpt.isPresent()) {
		    	Users signup = signupOpt.get();

		        
		        if (password.equals(signup.getPassword())) {
		           
		            return true;
		        }
		    }
		    return false;
	}


}
