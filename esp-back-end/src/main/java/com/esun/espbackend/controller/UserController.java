package com.esun.espbackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esun.espbackend.entity.UserEntity;
import com.esun.espbackend.service.UserService;

@RequestMapping(value = "user")
@RestController
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "all")
	public List<UserEntity> sample() {
        return userService.getAll();
    }
    
    @RequestMapping(value = "/get/{id}")
    public UserEntity getUser(@PathVariable(value = "id") String account) {
        return userService.getOne(account);
    }
 
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public UserEntity createUser(@Valid @RequestBody UserEntity UserEntity) {
        return userService.createUser(UserEntity);
    }
 
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSample(@PathVariable(value = "id") String account) {
        return userService.deleteUser(account);
    }
 
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public UserEntity updateSample(@PathVariable(value = "id") String account,
            @Valid @RequestBody UserEntity UserEntity) {
        return userService.updateUser(account, UserEntity);
    }
}
