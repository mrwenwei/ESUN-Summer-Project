package com.esun.espbackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esun.espbackend.entity.BanksEntity;
import com.esun.espbackend.repository.BanksRepository;

@Service
public class BanksService {
	@Autowired
	BanksRepository banksRepo;
	
	public List<BanksEntity> getAll() {
	    return (List<BanksEntity>) banksRepo.findAll();
	}
	
	public BanksEntity getOne(String SWIFTCODE) {
		Optional<BanksEntity> searchEntity = banksRepo.findById(SWIFTCODE);
		BanksEntity bank;
		if (searchEntity.isPresent()) {
            bank = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
		return bank;
	}
}
