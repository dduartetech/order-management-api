package com.diegoduarte.order_management_api.business.mapper;

import com.diegoduarte.order_management_api.business.dto.user.*;
import com.diegoduarte.order_management_api.business.dto.product.*;
import com.diegoduarte.order_management_api.business.dto.order.*;
import com.diegoduarte.order_management_api.infrastructure.entity.*;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppMapper {

    UserEntity toEntity(UserRequestDTO dto);
    UserResponseDTO toResponse(UserEntity entity);

    ProductEntity toEntity(ProductRequestDTO dto);
    ProductResponseDTO toResponse(ProductEntity entity);

    OrderResponseDTO toResponse(OrderEntity entity);

    OrderItemResponseDTO toResponse(OrderItemEntity entity);

}