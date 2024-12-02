package com.synectiks.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.synectiks.app.entity.RolesEntity;

@Repository
public interface RoleEntityRepository extends MongoRepository<RolesEntity, String> {

}
