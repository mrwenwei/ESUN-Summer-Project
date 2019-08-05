package com.esun.espbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esun.espbackend.entity.BanksEntity;

@Repository
public interface BanksRepository extends JpaRepository<BanksEntity, String> {

}
