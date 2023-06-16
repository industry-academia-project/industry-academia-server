package me.project.school.industry.academia.domain.user.controller;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.domain.user.dto.request.UserUpdateRequest;
import me.project.school.industry.academia.domain.user.dto.response.InfoResponse;
import me.project.school.industry.academia.domain.user.entity.User;
import me.project.school.industry.academia.global.jwt.TokenProvider;
import me.project.school.industry.academia.domain.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InfoResponse> userInfo(@RequestHeader("Authorization") String token ) {

        return ResponseEntity.ok().body(InfoResponse.builder().Message("회원정보 불러오기 성공").Status(HttpStatus.OK).user(userService.userInfo(token)).build());
    }

    @PutMapping("/info")
    @ResponseStatus(HttpStatus.OK)
    public void userUpdate(@RequestHeader("Authorization") String token, @RequestBody UserUpdateRequest request) {
        System.out.println(request);
        userService.userUpdate(token, request);
    }

}
