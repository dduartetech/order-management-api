package com.diegoduarte.order_management_api.business.dto.order;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemRequestDTO {

    private Long productId;
    private int quantity;

}