package com.esun.espbackend.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esun.espbackend.entity.EsunbranchEntity;
import com.esun.espbackend.repository.EsunbranchRepository;

@Service
public class EsunbranchService {
	
	@Autowired
	EsunbranchRepository esunbranchRepo;
	
	public List<EsunbranchEntity> getAll() {
		return esunbranchRepo.findAll();
	}
	
	public EsunbranchEntity getOne(String id) {
		Optional<EsunbranchEntity> searchEntity = esunbranchRepo.findById(id);
		EsunbranchEntity branch;
		if (searchEntity.isPresent()) {
            branch = searchEntity.get();
        } else {
            throw new EntityNotFoundException();
        }
		return branch;
	}
	
	public EsunbranchEntity createBranch(EsunbranchEntity branchEntity) {
		return esunbranchRepo.save(branchEntity);
	}
	
	public EsunbranchEntity updateBranch(String branchCode, EsunbranchEntity esunbranchEntity) {
		EsunbranchEntity updatedBranch;
        Optional<EsunbranchEntity> searchEntity = esunbranchRepo.findById(branchCode);
        if (searchEntity.isPresent()) {
            updatedBranch = esunbranchRepo.save(esunbranchEntity);
        } else {
            throw new EntityNotFoundException();
        }
        return updatedBranch;
    }
    
    public ResponseEntity<Object> deleteBranch(String branchCode) {
        Optional<EsunbranchEntity> esunbranchEntity = esunbranchRepo.findById(branchCode);
        if (esunbranchEntity.isPresent()) {
            EsunbranchEntity branch = esunbranchEntity.get();
            esunbranchRepo.delete(branch);
        } else {
            throw new EntityNotFoundException();
        }
        return ResponseEntity.ok().build();
    }
}
