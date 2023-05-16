package me.project.school.industry.academia.global.utils;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.global.properties.AppProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthorizationUtil {

    private final UserDetailsService userDetailsService;
    private final AppProperties appProperties;
    public static String AUTHORIZATION = "Authorization";

    public Authentication getAuthentication(String token) {
        String username = (String) Jwts.parser().setSigningKey(appProperties.getAccessSecret()).parseClaimsJws(token).getBody().get("userId");
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader(AUTHORIZATION);
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

//    public static String extract(HttpServletRequest request, String type) {
//
//        Enumeration<String> headers = request.getHeaders(AUTHORIZATION);
//
//        System.out.println(headers.nextElement());
//        while (headers.hasMoreElements()) {
//            String value = headers.nextElement();
//            if (value.toLowerCase().startsWith(type.toLowerCase())) {
//                return value.substring(type.length()).trim();
//            }
//        }
//
//        return Strings.EMPTY;
//    }
}
