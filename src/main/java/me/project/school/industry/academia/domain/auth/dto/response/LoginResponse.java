package me.project.school.industry.academia.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@Builder
public class LoginResponse {

    private String message;
    private HttpStatus status;


    private String accesstoken;
    private String refreshToken;
}
