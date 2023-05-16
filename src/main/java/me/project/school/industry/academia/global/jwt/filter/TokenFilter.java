package me.project.school.industry.academia.global.jwt.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.school.industry.academia.global.utils.AuthorizationUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Log4j2
public class TokenFilter extends OncePerRequestFilter {
    private final AuthorizationUtil authorizationUtil;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws IOException, ServletException {
            //String token = AuthorizationUtil.extract(request, "Bearer");
            String token = AuthorizationUtil.resolveToken(request);


        try {
            if (token != null) {
                Authentication auth = authorizationUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (IllegalStateException e) {
            log.info("Token validate Error : " + e);
        }

        filterChain.doFilter(request, response);
    }
}
