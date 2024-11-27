package com.synectiks.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.synectiks.app.entity.Entity;

public interface Repository extends MongoRepository<Entity, String> {

}
