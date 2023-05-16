package me.project.school.industry.academia.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.global.jwt.TokenProvider;
import me.project.school.industry.academia.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public void  userInfo() {
        userService.userInfo();
    }

//    @PutMapping("/info")
//    public void

    @GetMapping("/test")
    public void Testing() {
        System.out.println("come port:3000");
    }


}
