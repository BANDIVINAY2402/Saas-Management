package com.synectiks.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.synectiks.app.entity.Entity;
import com.synectiks.app.repository.Repository;

@org.springframework.stereotype.Service
public class Service {
	@Autowired
	private Repository rep;

	public void adddata(Entity entity) {
		
		rep.save(entity);
	}

}
