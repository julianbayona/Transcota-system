package com.transcotech.transcota_system.mapper;

import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderMapper {

    private static PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordEncoderMapper(PasswordEncoder passwordEncoder) {
        PasswordEncoderMapper.passwordEncoder = passwordEncoder;
    }

    @Named("encodePassword")
    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
