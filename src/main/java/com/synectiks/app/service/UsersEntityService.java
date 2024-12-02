package com.synectiks.app.service;

import java.util.Optional;

import javax.management.relation.Role;
import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synectiks.app.entity.RolesEntity;

import com.synectiks.app.entity.UsersEntity;
import com.synectiks.app.repository.RoleEntityRepository;
import com.synectiks.app.repository.UserEntityRepository;

@Service
public class UsersEntityService {
@Autowired
private UserEntityRepository userEntityRepository;
@Autowired
private RoleEntityRepository rolerepo;
	public String createuser(UsersEntity usersentity) throws RoleNotFoundException {
		System.out.println(userEntityRepository.findByEmail(usersentity.getEmail()));
		
		 // Fetch the role from the database (ensure the role exists)
	    RolesEntity role = rolerepo.findById(usersentity.getRole().getId())
	                                    .orElseThrow(() -> new RoleNotFoundException("Role not found"));

		if(userEntityRepository.findByEmail(usersentity.getEmail())!=null)
		{
			return "user already exist";
		}
		else {
			UsersEntity users=new UsersEntity();
			users.setEmail(usersentity.getEmail());
			users.setUsername(usersentity.getUsername());
			users.setPassword(usersentity.getPassword());
     		users.setRole(role);
			System.out.println(users);
     		userEntityRepository.save(users);
     		
     		System.out.println(userEntityRepository.findAll());
			return "user registered successfully";
		}
	
	}
	public boolean signIn(String email, String password) {
		 Optional<UsersEntity> signupOpt = Optional.ofNullable(userEntityRepository.findByEmail(email));

		    if (signupOpt.isPresent()) {
		    	UsersEntity signup = signupOpt.get();

		        
		        if (password.equals(signup.getPassword())) {
		           
		            return true;
		        }
		    }
		    return false;
	}

}
