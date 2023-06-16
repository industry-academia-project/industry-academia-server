package me.project.school.industry.academia.global.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.school.industry.academia.domain.user.entity.User;
import me.project.school.industry.academia.domain.user.repository.UserRepository;
import me.project.school.industry.academia.domain.auth.exception.AuthException;
import me.project.school.industry.academia.global.properties.AppProperties;
import me.project.school.industry.academia.global.enums.JwtAuth;
import me.project.school.industry.academia.global.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final AppProperties appProperties;
    private final UserRepository userRepository;
    private static final long JWT_ACCESS_EXPIRE = 1000 * 60 * 60;
    private static final long JWT_REFRESH_EXPIRE = 1000 * 60 * 60 * 24;
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String generateToken(String userId, JwtAuth jwtAuth) {
        Date expiredAt = new Date();
        expiredAt = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? new Date(expiredAt.getTime() + JWT_ACCESS_EXPIRE)
                : new Date(expiredAt.getTime() + JWT_REFRESH_EXPIRE);

        String secretKey = (jwtAuth == JwtAuth.ACCESS_TOKEN)
                ? appProperties.getAccessSecret()
                : appProperties.getRefreshSecret();

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(jwtAuth.toString())
                .setIssuedAt(new Date())
                .setExpiration(expiredAt)
                .signWith(SIGNATURE_ALGORITHM, secretKey)
                .compact();
    }

    private Claims parseToken(String token, JwtAuth jwtAuth) {
        try {
            return Jwts.parser()
                    .setSigningKey((jwtAuth == JwtAuth.ACCESS_TOKEN)
                            ? appProperties.getAccessSecret()
                            : appProperties.getRefreshSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "위조된 토큰입니다.");
        } catch (IllegalArgumentException e) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        } catch (Exception e) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "토큰 서비스와의 오류가 발생하였습니다.");
        }
    }

    private String authorizedBearer(String token) {
        return token.replaceAll("^Bearer( )*", "");
    }

    public User validateToken(String token) {

        return userRepository.findByEmail(
                String.valueOf(parseToken(authorizedBearer(token), JwtAuth.ACCESS_TOKEN)
                        .get("userId")
                        .toString())
        ).orElseThrow(AuthException.NotFoundUserException::new);
    }

    public String refreshToken(String refreshToken) {
        if (refreshToken == null || refreshToken.trim().isEmpty()) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "토큰이 존재하지 않습니다.");
        }

        Claims claims = parseToken(refreshToken, JwtAuth.REFRESH_TOKEN);

        User user = userRepository
                .findByEmail(claims.getId())
                .orElseThrow(AuthException.NotFoundUserException::new);
        return generateToken(user.getEmail(), JwtAuth.ACCESS_TOKEN);
    }
}
