package com.diegoduarte.order_management_api.business.dto.order;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemResponseDTO {

    private Long productId;
    private String productName;
    private int quantity;
    private BigDecimal price;

}