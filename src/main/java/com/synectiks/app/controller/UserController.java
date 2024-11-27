package com.synectiks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.synectiks.app.entity.Users;
import com.synectiks.app.repository.UserRepository;
import com.synectiks.app.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userservice;
	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("signup")
	public ResponseEntity<String> uploadCsv(@RequestBody Users signupdetails) {
	    try {
	        String response=userservice.Signup(signupdetails);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        try {
    
        	userservice.sendOtpToUser(email);
        	
            return ResponseEntity.ok("OTP sent to your email.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    @PostMapping("/validate-otp")
    public ResponseEntity<?> validateOtp(@RequestParam String email, @RequestParam String otp) {
        if (userservice.validateOtp(email, otp)) {
            return ResponseEntity.ok("OTP verified.");
        } else {
            return ResponseEntity.status(401).body("Invalid or expired OTP.");
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String email, @RequestParam String otp, @RequestParam String newPassword) {
        try {
        	userservice.resetPassword(email, otp, newPassword);
            return ResponseEntity.ok("Password reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password) {
        boolean success = userservice.signIn(email, password);
System.out.println(success);
        if (success) {
        	Users studentdetails=	userrepo.findByEmail(email);
     String studentId=studentdetails.getUserid();
     String name=studentdetails.getLastname();
 
            return ResponseEntity.ok("login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }
	

}
