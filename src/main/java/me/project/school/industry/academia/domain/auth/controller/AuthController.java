package me.project.school.industry.academia.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.school.industry.academia.domain.auth.dto.request.AuthRequest;
import me.project.school.industry.academia.domain.auth.dto.request.LoginRequest;
import me.project.school.industry.academia.domain.auth.dto.response.AuthResponse;
import me.project.school.industry.academia.domain.auth.dto.response.LoginResponse;
import me.project.school.industry.academia.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthResponse> signup(@RequestBody @Valid AuthRequest request){
        authService.signup(request);

        return ResponseEntity.ok().body(AuthResponse.builder().message("회원가입 되었습니다.").status(HttpStatus.CREATED).build());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<LoginResponse>  login(@RequestBody @Valid LoginRequest request) {
         LoginResponse res = authService.login(request);

         return ResponseEntity.ok().body(res);
    }



}
