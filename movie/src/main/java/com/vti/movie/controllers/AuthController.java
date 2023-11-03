package com.vti.movie.controllers;

import com.vti.movie.config.jwt.JWTTokenUtils;
import com.vti.movie.dtos.LoginDTO;
import com.vti.movie.dtos.SignUpDTO;
import com.vti.movie.entity.User;
import com.vti.movie.exception.AppException;
import com.vti.movie.exception.ErrorResponseBase;
import com.vti.movie.modal.LoginRequest;
import com.vti.movie.repository.IUserRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login-v2")
    public LoginDTO loginJWT(@RequestBody LoginRequest request) {
        Optional<User> userOptional = userRepository.findByUsername(request.getUsername());
        if (userOptional.isEmpty()) {
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_USERNAME);
        }
        // Kiểm tra xem password người dùng truyền vào có đúng hay k
        if (!encoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            //Nếu k khớp password -> bắn ra lỗi
            throw new AppException(ErrorResponseBase.lOGIN_FAILS_PASSWORD);
        }

        //Tạo ra đối tượng trả về
        LoginDTO loginDto = new LoginDTO();
        BeanUtils.copyProperties(userOptional.get(), loginDto);
        loginDto.setUserAgent(httpServletRequest.getHeader("User-Agent"));// Lấy thông tin trình duyệt đang sử dụng
        String token = jwtTokenUtils.createAccessToken(loginDto); // Tạo token
        loginDto.setToken(token); // set giá trị token vào loginDto để trả về cho người dùng sử dụng
        return loginDto;
    }

    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody User user){
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new Exception("Đã tồn tại người dùng, vui lòng chọn tên người dùng khác");
            }
            String password = user.getPassword();
            userRepository.save(user);
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenUtils.generateTokenLogin(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User currentUser = userRepository.findByUsername(user.getUsername()).get();
            return ResponseEntity.ok(new SignUpDTO(currentUser.getId(), currentUser.getFullName(), userDetails.getUsername() , currentUser.getEmail(), currentUser.getRole(), currentUser.getPhoneNumber()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

