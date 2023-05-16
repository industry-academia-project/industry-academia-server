package me.project.school.industry.academia.global.config;

import lombok.RequiredArgsConstructor;
import me.project.school.industry.academia.global.utils.AuthorizationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthorizationUtil authorizationUtil;

    @Override
    public void configure (HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(configurationSource()).and()
                .httpBasic().disable() // rest api 이므로  기본설정 안함
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/info").hasRole("USER")
                .anyRequest().permitAll();

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http
//                .exceptionHandling()
//                .accessDeniedHandler()
//                .authenticationEntryPoint()

        http
                .apply(new TokenSecurityConfig(authorizationUtil));

    }

    @Bean
    CorsConfigurationSource configurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}
