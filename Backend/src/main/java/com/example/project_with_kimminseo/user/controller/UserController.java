package com.example.project_with_kimminseo.user.controller;

import com.example.project_with_kimminseo.user.controller.request.RegisterRequest;
import com.example.project_with_kimminseo.user.model.entity.UserEntity;
import com.example.project_with_kimminseo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "User 회원가입")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원이 생성된거임")
    })
    public ResponseEntity<UserEntity> register(@Valid @RequestBody RegisterRequest registerRequest){
        log.info("request : {}", registerRequest);
        UserEntity register = userService.register(registerRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(register);
    }
}
