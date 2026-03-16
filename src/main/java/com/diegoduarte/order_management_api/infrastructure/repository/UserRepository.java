package com.diegoduarte.order_management_api.infrastructure.repository;

import com.diegoduarte.order_management_api.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
