package com.esun.espbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.esun.espbackend.entity.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}
