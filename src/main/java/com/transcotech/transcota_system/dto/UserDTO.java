package com.transcotech.transcota_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long personId;
    private String roleDTO;
    private String name;
    private String email;
}
