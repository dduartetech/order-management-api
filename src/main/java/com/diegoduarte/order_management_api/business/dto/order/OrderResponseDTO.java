package com.diegoduarte.order_management_api.business.dto.order;

import com.diegoduarte.order_management_api.infrastructure.enums.StatusEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long id;
    private StatusEnum status;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<OrderItemResponseDTO> items;

}