package com.synectiks.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.app.entity.Entity;
import com.synectiks.app.service.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class Controller {
@Autowired
private Service ser;

@PostMapping("/add")
public String postMethodName(@RequestBody Entity entity) {
    
    ser.adddata(entity);
    return "data added successfully";
}

}
