package com.esun.espbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.esun.espbackend.entity.TransactionEntity;
import com.esun.espbackend.service.TransactionService;

@RestController
@CrossOrigin
public class TransactionController {
	@Autowired
	TransactionService transService;
	
	@RequestMapping(value = "/GET/transactions")
	public List<TransactionEntity> getAll() {
        return transService.getAll();
    }
	
	@RequestMapping(value = "/GET/transaction/{id}")
    public TransactionEntity getUser(@PathVariable(value = "id") String id) {
        return transService.getOne(id);
    }
	
	@RequestMapping(value = "/POST/transaction", method = RequestMethod.POST)
    public TransactionEntity createUser(@Valid @RequestBody TransactionEntity transEntity) {
        return transService.createTransaction(transEntity);
    }
	
	@RequestMapping(value = "/DELETE/transaction/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSample(@PathVariable(value = "id") String id) {
        return transService.deleteTransaction(id);
    }
	
	@RequestMapping(value = "/PUT/transaction/{id}", method = RequestMethod.PUT)
    public TransactionEntity updateUser(@PathVariable(value = "id") String id,
            @Valid @RequestBody TransactionEntity transEntity) {
        return transService.updateTransaction(id, transEntity);
    }
}
