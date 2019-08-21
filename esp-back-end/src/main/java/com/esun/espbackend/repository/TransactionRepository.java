package com.esun.espbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.esun.espbackend.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {
	@Query(nativeQuery = true,
            value = "select * from Transactions where branch_code=?1")
	List<TransactionEntity> getSameBranchTransactions(String branch_code);
}
