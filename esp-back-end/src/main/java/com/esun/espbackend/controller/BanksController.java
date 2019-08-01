package com.esun.espbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esun.espbackend.entity.BanksEntity;
import com.esun.espbackend.service.BanksService;

@RestController
@CrossOrigin
public class BanksController {
	@Autowired
	BanksService banksservice;
	
	@RequestMapping(value = "/GET/banks")
	public List<BanksEntity> getAll() {
        return banksservice.getAll();
    }
	
	@RequestMapping(value = "/GET/bank")
	public BanksEntity getBank(@PathVariable(value = "bank") String SWIFTCODE) {
		return banksservice.getOne(SWIFTCODE);
	}
}
