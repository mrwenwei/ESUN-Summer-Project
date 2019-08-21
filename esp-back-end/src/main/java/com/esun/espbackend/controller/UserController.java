package com.esun.espbackend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esun.espbackend.entity.UserEntity;
import com.esun.espbackend.service.UserService;

@RestController
@CrossOrigin
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/GET/users")
	public List<UserEntity> getAll() {
    	return userService.getAll();
    }
    
    @RequestMapping(value = "/GET/user/{id}")
    public UserEntity getUser(@PathVariable(value = "id") String account) {
        return userService.getOne(account);
    }
    
    @RequestMapping(value = "/POST/user/auth/{account}", method = RequestMethod.POST)
    public UserEntity authenticateUser(@PathVariable(value = "account") String account,
    		@Valid @RequestBody UserEntity userEntity) {
    	return userService.authenticateUser(account, userEntity);
    }
 
    @RequestMapping(value = "/POST/user", method = RequestMethod.POST)
    public UserEntity createUser(@Valid @RequestBody UserEntity userEntity) {
        return userService.createUser(userEntity);
    }
 
    @RequestMapping(value = "/DELETE/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSample(@PathVariable(value = "id") String account) {
        return userService.deleteUser(account);
    }
 
    @RequestMapping(value = "/PUT/user/{id}", method = RequestMethod.PUT)
    public UserEntity updateUser(@PathVariable(value = "id") String account,
            @Valid @RequestBody UserEntity userEntity) {
        return userService.updateUser(account, userEntity);
    }
}
