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

import com.esun.espbackend.entity.EsunbranchEntity;
import com.esun.espbackend.service.EsunbranchService;

@RestController
@CrossOrigin
public class EsunbranchController {
	@Autowired
	EsunbranchService branchService;
	
	@RequestMapping(value = "/GET/branches")
	public List<EsunbranchEntity> getAll() {
        return branchService.getAll();
    }
	
	@RequestMapping(value = "/GET/branch/{branch_code}")
    public EsunbranchEntity getUser(@PathVariable(value = "branch_code") String branch_code) {
        return branchService.getOne(branch_code);
    }
	
	@RequestMapping(value = "/POST/branch", method = RequestMethod.POST)
    public EsunbranchEntity createUser(@Valid @RequestBody EsunbranchEntity branchEntity) {
        return branchService.createBranch(branchEntity);
    }
	
	@RequestMapping(value = "/DELETE/branch/{branch_code}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBranch(@PathVariable(value = "branch_code") String branch_code) {
        return branchService.deleteBranch(branch_code);
    }
	
	@RequestMapping(value = "/PUT/branch/{branch_code}", method = RequestMethod.PUT)
    public EsunbranchEntity updateUser(@PathVariable(value = "branch_code") String branch_code,
            @Valid @RequestBody EsunbranchEntity branchEntity) {
        return branchService.updateBranch(branch_code, branchEntity);
    }
}
