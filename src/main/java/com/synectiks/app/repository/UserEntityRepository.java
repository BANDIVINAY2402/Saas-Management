package com.synectiks.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.synectiks.app.entity.UsersEntity;

@Repository
public interface UserEntityRepository extends MongoRepository<UsersEntity, String> {

	UsersEntity findByEmail(String email);

}
