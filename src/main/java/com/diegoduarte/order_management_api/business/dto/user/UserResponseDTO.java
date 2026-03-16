package com.diegoduarte.order_management_api.business.dto.user;

import com.diegoduarte.order_management_api.infrastructure.enums.RoleEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private RoleEnum role;
    private LocalDateTime createdAt;
}
