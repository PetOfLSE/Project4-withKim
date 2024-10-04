package com.example.project_with_kimminseo.common.converter;

import com.example.project_with_kimminseo.common.enums.Role;
import com.example.project_with_kimminseo.user.controller.request.RegisterRequest;
import com.example.project_with_kimminseo.user.model.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class Converter {

    private final PasswordEncoder passwordEncoder;

    public UserEntity toUserEntity(RegisterRequest request){
        return UserEntity.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .gender(request.getGender())
                .phoneNumber(request.getPhoneNumber())
                .registerAt(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .build();
    }
}
