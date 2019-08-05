package com.esun.espbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esun.espbackend.entity.EsunbranchEntity;

@Repository
public interface EsunbranchRepository extends JpaRepository<EsunbranchEntity, String> {

}
