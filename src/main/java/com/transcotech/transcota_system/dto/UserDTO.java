package com.transcotech.transcota_system.dto;

import com.transcotech.transcota_system.model.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long personId;
    @Enumerated(EnumType.STRING)
    private Role roleDTO;
    private String name;
    private String email;
}
