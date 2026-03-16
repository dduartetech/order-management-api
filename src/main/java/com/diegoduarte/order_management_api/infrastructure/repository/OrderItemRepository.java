package com.diegoduarte.order_management_api.infrastructure.repository;

import com.diegoduarte.order_management_api.infrastructure.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {
}
