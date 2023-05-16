package me.project.school.industry.academia.domain.auth.service;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.domain.auth.dto.request.AuthRequest;
import me.project.school.industry.academia.domain.auth.dto.response.LoginResponse;
import me.project.school.industry.academia.domain.user.repository.UserRepository;
import me.project.school.industry.academia.domain.auth.exception.AuthException;
import me.project.school.industry.academia.domain.user.entity.User;
import me.project.school.industry.academia.global.enums.JwtAuth;
import me.project.school.industry.academia.global.enums.UserRole;
import me.project.school.industry.academia.global.jwt.TokenProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;


    public void Signup(AuthRequest request)  {
        if(userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException.AlreadyUserException();
        }

        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(request.getPassword())
                .userRole(UserRole.valueOf("USER"))
                .build();
        userRepository.save(user);
    }

    public LoginResponse login(AuthRequest request) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(5);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(AuthException.NotFoundUserException::new);

        if(!bcrypt.matches(request.getPassword(), user.getPassword())) {
            throw new AuthException.NotInaccurateInfo();
        }

        String accessToken = tokenProvider.generateToken(user.getEmail(), JwtAuth.ACCESS_TOKEN);
        String refreshToken = tokenProvider.generateToken(user.getPassword(), JwtAuth.REFRESH_TOKEN);

        return LoginResponse.builder()
                .accesstoken(accessToken)
                .refreshToken(refreshToken)
                .build();


    }
}
