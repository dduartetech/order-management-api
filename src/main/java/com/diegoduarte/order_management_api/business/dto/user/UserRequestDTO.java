package com.diegoduarte.order_management_api.business.dto.user;

import com.diegoduarte.order_management_api.infrastructure.enums.RoleEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    private String name;
    private String email;
    private String password;
    private RoleEnum role;

}
