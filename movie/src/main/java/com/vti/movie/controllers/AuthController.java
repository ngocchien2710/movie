package com.vti.movie.controllers;

import com.vti.movie.config.jwt.JWTTokenUtils;
import com.vti.movie.dtos.LoginDTO;
import com.vti.movie.entity.User;
import com.vti.movie.exception.AppException;
import com.vti.movie.exception.ErrorResponseBase;
import com.vti.movie.modal.LoginRequest;
import com.vti.movie.repository.IUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
@Validated
public class AuthController {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JWTTokenUtils jwtTokenUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/login-v2")
    public LoginDTO loginJWT(@RequestBody LoginRequest request) {
        Optional<User> accountOptional = userRepository.findByUsername(request.getUsername());
        if (accountOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_USERNAME);
        }
        // Kiểm tra xem password người dùng truyền vào có đúng hay k
        if (!encoder.matches(request.getPassword(), accountOptional.get().getPassword())) {
            //Nếu k khớp password -> bắn ra lỗi
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_PASSWORD);
        }

        //Tạo ra đối tượng trả về
        LoginDTO loginDto = new LoginDTO();
        BeanUtils.copyProperties(accountOptional.get(), loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));// Lấy thông tin trình duyệt đang sử dụng
        String token = jwtTokenUtils.createAccessToken(loginDto); // Tạo token
        loginDto.setToken(token); // set giá trị token vào loginDto để trả về cho người dùng sử dụng
        return loginDto;
    }
}
