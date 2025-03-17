package com.transcotech.transcota_system.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.LoginDTO;
import com.transcotech.transcota_system.model.Login;

@Mapper
public interface LoginMapper {
    
    LoginMapper INSTANCE = Mappers.getMapper(LoginMapper.class);

    LoginDTO LoginToLoginDTO(Login Login);

    Login LoginDTOToLogin(LoginDTO loginDTO);
}
