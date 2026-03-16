package com.diegoduarte.order_management_api.infrastructure.repository;

import com.diegoduarte.order_management_api.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
