package com.esun.espbackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esun.espbackend.entity.UserEntity;
import com.esun.espbackend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public List<UserEntity> getAll() {
	    return (List<UserEntity>) userRepo.findAll();
	}
	
	public UserEntity getOne(String account) {
		Optional<UserEntity> searchEntity = userRepo.findById(account);
		UserEntity User;
		if (searchEntity.isPresent()) {
            User = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
		return User;
	}
	
    public UserEntity createUser(UserEntity userEntity) {
        return userRepo.save(userEntity);
    }
    
    public UserEntity updateUser(String account, UserEntity userEntity) {
        UserEntity updatedUser;
        Optional<UserEntity> searchEntity = userRepo.findById(account);
        if (searchEntity.isPresent()) {
            UserEntity User = searchEntity.get();
            User.setPassword(userEntity.getPassword());
            User.setName(userEntity.getName());
            User.setRole(userEntity.getRole());
            User.setBranchCode(userEntity.getBranchCode());
            updatedUser = userRepo.save(User);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedUser;
    }
    
    public ResponseEntity<Object> deleteUser(String account) {
        Optional<UserEntity> UserEntity = userRepo.findById(account);
        if (UserEntity.isPresent()) {
            UserEntity User = UserEntity.get();
            userRepo.delete(User);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
    
    public UserEntity authenticateUser(String account, UserEntity accountEntity) {
		Optional<UserEntity> searchEntity = userRepo.findById(account);
		if (searchEntity.isPresent()) {
			UserEntity login_account = searchEntity.get();
            if (accountEntity.getPassword().equals(login_account.getPassword())) {
            	
            	return login_account;
            }else {
            	accountEntity.setRole("Wrong password");
            	return accountEntity;
            }    
        } else {
        	accountEntity.setRole("Wrong account");
        	return accountEntity;
        }
    }
}
