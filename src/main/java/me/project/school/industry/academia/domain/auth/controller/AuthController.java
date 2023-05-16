package me.project.school.industry.academia.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.school.industry.academia.domain.auth.dto.request.AuthRequest;
import me.project.school.industry.academia.domain.auth.dto.response.LoginResponse;
import me.project.school.industry.academia.domain.auth.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class AuthController {

    private final AuthService authService;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void Signup(@RequestBody @Valid AuthRequest request){
        authService.Signup(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@RequestBody @Valid AuthRequest request) {
         return authService.login(request);
    }



}
