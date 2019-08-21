package com.esun.espbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

import javax.validation.Valid;
import javax.persistence.NoResultException;
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
	
	@RequestMapping(value = "/GET/alltransactions")
	public List<TransactionEntity> getAll(@RequestHeader (name="Authorization") String token) {
		if (token.equals("manager") || token.equals("teller"))
			return transService.getAll();
		else
			throw new NoResultException();
    }
	
	@RequestMapping(value = "/GET/transactions/{branch_code}")
	public List<TransactionEntity> getSameBranchTransactions(@PathVariable(value = "branch_code") String branch_code,
			@RequestHeader (name="Authorization") String token) {
		if (token.equals("manager") || token.equals("teller"))
			return transService.getSameBranchTransactions(branch_code);
		else
			throw new NoResultException();	
    }
	
	@RequestMapping(value = "/GET/transaction/{id}")
    public TransactionEntity getTransaction(@PathVariable(value = "id") String id) {
        return transService.getOne(id);
    }
	
	@RequestMapping(value = "/POST/transaction", method = RequestMethod.POST)
    public TransactionEntity createTransaction(@Valid @RequestBody TransactionEntity transEntity) {
        return transService.createTransaction(transEntity);
    }
	
	@RequestMapping(value = "/DELETE/transaction/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id") String id) {
        return transService.deleteTransaction(id);
    }
	
	@RequestMapping(value = "/PUT/transaction/{id}", method = RequestMethod.PUT)
    public TransactionEntity updateTransaction(@PathVariable(value = "id") String id,
            @Valid @RequestBody TransactionEntity transEntity) {
        return transService.updateTransaction(id, transEntity);
    }
}
