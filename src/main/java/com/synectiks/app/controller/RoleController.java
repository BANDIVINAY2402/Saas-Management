package com.synectiks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.app.entity.RolesEntity;

import com.synectiks.app.service.RolesEntityService;
import com.synectiks.app.service.UsersEntityService;

@RestController
public class RoleController {
	@Autowired
	private RolesEntityService rolesEntityService;
	
	@Autowired
	private UsersEntityService usersEntityService;
	
	@PostMapping("create-roles")
	public ResponseEntity<String> createRoles(@RequestBody RolesEntity roleEntity) {
	    try {
	        String response=rolesEntityService.createRoles(roleEntity);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
	    }
	}

}
