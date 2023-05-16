package me.project.school.industry.academia.global.config;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.global.utils.AuthorizationUtil;
import me.project.school.industry.academia.global.jwt.filter.TokenFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class TokenSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    //private final TokenProvider tokenProvider;
    private final AuthorizationUtil authorizationUtil;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        TokenFilter tokenFilter = new TokenFilter(authorizationUtil);
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
