package com.vti.movie.config.jwt;

import com.vti.movie.dtos.LoginDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private JWTTokenUtils jwtTokenUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    @NotNull HttpServletResponse httpServletResponse,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        //Lấy token từ api (request)
        String token = httpServletRequest.getHeader(AUTHORIZATION);
        String request = httpServletRequest.getRequestURI();

        if (StringUtils.containsAnyIgnoreCase(request, "/api/v1/auth/login")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-ui")
                || StringUtils.containsAnyIgnoreCase(request, "/swagger-resources")
                || StringUtils.containsAnyIgnoreCase(request, "/v3/api-docs"))
        {
            // Những api public k cần check token -> doFilter
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            if (jwtTokenUtils.checkToken(token, httpServletResponse, httpServletRequest)) {
                //Giải mã token -> lấy thông tin user -> authen
                LoginDTO loginDto = new LoginDTO();

                //Lấy danh sách quyền của user
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(loginDto.getRole());

                //Tạo đối tượng để authen vào hệ thống
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }
        }

    }
}
