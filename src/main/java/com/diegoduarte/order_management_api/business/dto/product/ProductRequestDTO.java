package com.diegoduarte.order_management_api.business.dto.product;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
}
