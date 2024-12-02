      package com.synectiks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.synectiks.app.entity.UsersEntity;
import com.synectiks.app.service.UsersEntityService;

@RestController
public class LoginController {
	@Autowired
	private UsersEntityService users;

    @PostMapping("/sign-in")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password) {
        boolean success = users.signIn(email, password);

        if (success) {
 
            return ResponseEntity.ok("login success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password.");
        }
    }
	

}
