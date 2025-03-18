package com.transcotech.transcota_system.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.transcotech.transcota_system.dto.UserDTO;
import com.transcotech.transcota_system.model.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
        @Mapping(source = "role", target = "roleDTO"),
        @Mapping(source = "personId", target = "personId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "email", target = "email")
    })
    UserDTO userToUserDTO(User user);
    @Mappings({
        @Mapping(source = "roleDTO", target = "role"),
        @Mapping(source = "personId", target = "personId"),
        @Mapping(source = "name", target = "name"),
        @Mapping(source = "email", target = "email")
    })
    User userDTOToUser(UserDTO userDTO);

    List<UserDTO> usersToUserDTOs(List<User> users);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);
}

