package com.synectiks.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.synectiks.app.entity.Users;

@Repository
public interface UserRepository extends MongoRepository<Users, Long> {

	Users findByEmail(String email);

}
