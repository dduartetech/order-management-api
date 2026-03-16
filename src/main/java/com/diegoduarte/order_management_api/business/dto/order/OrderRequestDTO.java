package com.diegoduarte.order_management_api.business.dto.order;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {

    private Long userId;
    private List<OrderItemRequestDTO> items;

}