package com.synectiks.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synectiks.app.entity.RolesEntity;

import com.synectiks.app.repository.RoleEntityRepository;

@Service
public class RolesEntityService {

	@Autowired
	private RoleEntityRepository rolerepo;
	public String createRoles(RolesEntity roleEntity) {
	
		
		RolesEntity roles=new RolesEntity();
		roles.setName(roleEntity.getName());;
		roles.setPermissions(roleEntity.getPermissions());;
//		roles.setLastname(signupdetails.getLastname());
//		roles.setPassword(signupdetails.getPassword());
//			System.out.println(signup);
			rolerepo.save(roles);
			
			return "user registered successfully";
	
	}

}
