package com.esun.espbackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esun.espbackend.entity.TransactionEntity;
import com.esun.espbackend.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transRepo;
	
	public List<TransactionEntity> getAll() {
		return transRepo.findAll();
	}
	
	public TransactionEntity getOne(String id) {
		Optional<TransactionEntity> searchEntity = transRepo.findById(id);
		TransactionEntity Transaction;
		if (searchEntity.isPresent()) {
            Transaction = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
		return Transaction;
	}
	
	public TransactionEntity createTransaction(TransactionEntity transactionEntity) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(transactionEntity.getDateTime());
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		sdf.setTimeZone(TimeZone.getTimeZone("CST"));
		return transRepo.save(transactionEntity);
	}
	
	public TransactionEntity updateTransaction(String id, TransactionEntity transactionEntity) {
        TransactionEntity updatedTransaction;
        Optional<TransactionEntity> searchEntity = transRepo.findById(id);
        if (searchEntity.isPresent()) {
            TransactionEntity Transaction = searchEntity.get();
            Transaction.setDateTime(transactionEntity.getDateTime());
            Transaction.setBranchCode(transactionEntity.getBranchCode());
            Transaction.setType(transactionEntity.getType());
            Transaction.setBroker(transactionEntity.getBroker());
            Transaction.setReceiptsData(transactionEntity.getReceiptsData());
            updatedTransaction = transRepo.save(Transaction);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedTransaction;
    }
    
    public ResponseEntity<Object> deleteTransaction(String id) {
        Optional<TransactionEntity> transactionEntity = transRepo.findById(id);
        if (transactionEntity.isPresent()) {
            TransactionEntity Transaction = transactionEntity.get();
            transRepo.delete(Transaction);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
    

}
