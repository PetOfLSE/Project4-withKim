package com.example.project_with_kimminseo.user.service;

import com.example.project_with_kimminseo.common.converter.Converter;
import com.example.project_with_kimminseo.user.controller.request.RegisterRequest;
import com.example.project_with_kimminseo.user.model.entity.UserEntity;
import com.example.project_with_kimminseo.user.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Converter converter;

    public UserEntity register(RegisterRequest registerRequest) {
        log.info("Register request: {}", registerRequest);
        UserEntity userEntity = converter.toUserEntity(registerRequest);
        log.info("UserEntity: {}", userEntity);
        UserEntity save = userRepository.save(userEntity);

        return save;
    }
}
