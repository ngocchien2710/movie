package com.vti.movie.config.jwt;

import com.alibaba.fastjson.JSON;

import com.vti.movie.modal.dtos.LoginDTO;

import com.vti.movie.modal.dtos.SignUpDTO;
import com.vti.movie.modal.entity.Token;
import com.vti.movie.exception.AppException;
import com.vti.movie.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Component
public class JWTTokenUtils {
    private static final long EXPIRATION_TIME = 864000000; //10 DAYS
    private static final String SECRET = "123456";// Chữ ký bí mật
    private static final String PREFIX_TOKEN = "Bearer";//Ký tự đầu tiên của token
    private static final String AUTHORIZATION = "Authorization";//key của token trên header


    private long expDate = 864000000;

    @Autowired
    private TokenRepository tokenRepository;



    //Hàm này dùng để tạo ra token
    public String createAccessToken(LoginDTO loginDto) {
        //Tạo giá trị thời hạn token( bằng thời gian hiện tại + 10 ngày hoặc tùy theo)
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setId(String.valueOf(loginDto.getId())) // set giá trị id
                .setSubject(loginDto.getUsername()) // set giá trị subject
                .setIssuedAt(new Date())
                .setIssuer("VTI")
                .setExpiration(expirationDate) // set thời hạn của token
                .signWith(SignatureAlgorithm.HS512, SECRET) // khai báo phương thức mã hóa token và chữ ký bí mật
                .claim("authorities",loginDto.getRole().name()) // thêm trường authorities để lưu giá trị phân quyền
                .claim("userAgent", loginDto.getUserAgent()).compact(); // thêm trường userAgent để lưu thông tin trình duyệt đang dùng

        Token tokenEntity = new Token();
        tokenEntity.setToken(token);
        tokenEntity.setExpiration(expirationDate);
        tokenEntity.setUserAgent(loginDto.getUserAgent());
        tokenRepository.save(tokenEntity);
        return token;
    }

    // Hàm này dùng để giải mã hóa token
    public LoginDTO parseAccessToken(String token) {
        LoginDTO loginDto = new LoginDTO();
        if (!token.isEmpty()) {
            try {
                token = token.replace(PREFIX_TOKEN, "").trim();
                Claims claims = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token).getBody();
                //Lấy ra các thông tin
                String user = claims.getSubject();

                String userAgent = claims.get("userAgent").toString();

                //Gán các thông tin vào đối tượng LoginDto, có thể sử dụng constructor
                loginDto.setUsername(user);

                loginDto.setUserAgent(userAgent);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return loginDto;
    }

    public boolean checkToken(String token, @NotNull HttpServletResponse response, HttpServletRequest httpServletRequest) {
        try {
            if (StringUtils.isBlank(token) || !token.startsWith(PREFIX_TOKEN)) { // token bị trống -> lỗi
                responseJson(response, new AppException("Token ko hợp lệ", 401, httpServletRequest.getRequestURI()));
                return false;
            }
            token = token.replace(PREFIX_TOKEN, "").trim();

            Token tokenEntity = tokenRepository.findByToken(token);
            if (tokenEntity == null) { // Ko có token trên hệ thống
                responseJson(response, new AppException("Token ko tồn tại", 401, httpServletRequest.getRequestURI()));
                return false;
            }

//            String ip = httpServletRequest.getHeader("User-Agent");
//            if (!StringUtils.equals(ip, tokenEntity.getUserAgent())) { // Token sử dụng ở một nơi khác
//                responseJson(response, new AppException("Token đc dùng ở chỗ khác",401, httpServletRequest.getRequestURI()));
//                return false;
//            }

            if (tokenEntity.getExpiration().after(new Date(System.currentTimeMillis() + EXPIRATION_TIME))) { // Token hết hạn
                responseJson(response, new AppException("Token hết hiệu lực", 401, httpServletRequest.getRequestURI()));
                return false;
            }
        } catch (Exception e) {
            responseJson(response, new AppException(e.getMessage(), 401, httpServletRequest.getRequestURI()));
            return false;
        }
        return true;
    }


    //Hàm này dùng để response dữ liệu khi gặp lỗi
    private void responseJson(HttpServletResponse response, AppException appException) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(appException.getCode());
        try {
            response.getWriter().print(JSON.toJSONString(appException));
        } catch (IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public String generateTokenLogin(String username) {
//       SignUpDTO signUpDTO = (SignUpDTO) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}