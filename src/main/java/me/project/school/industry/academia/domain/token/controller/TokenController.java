package me.project.school.industry.academia.domain.token.controller;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.global.jwt.TokenProvider;
import me.project.school.industry.academia.domain.token.dto.request.RefreshTokenRequest;
import me.project.school.industry.academia.domain.token.dto.response.TokenResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {

    private final TokenProvider tokenProvider;

    @PostMapping("/refresh")
    public TokenResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return new TokenResponse(tokenProvider.refreshToken(request.getRefreshToken()),
                "토큰이 재발급되었습니다."
        );
    }
}
