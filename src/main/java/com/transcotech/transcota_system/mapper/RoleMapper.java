package com.transcotech.transcota_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.RoleDTO;
import com.transcotech.transcota_system.model.AdminRole;
import com.transcotech.transcota_system.model.DriverRole;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(DriverRole role);

    AdminRole roleDTOToAdminRole(RoleDTO roleDTO);
    DriverRole roleDTOToDriverRole(RoleDTO roleDTO);
}
