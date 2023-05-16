package me.project.school.industry.academia.domain.auth.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class AuthRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private String name;
}
