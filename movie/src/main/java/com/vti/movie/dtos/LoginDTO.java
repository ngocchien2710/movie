package com.vti.movie.dtos;

import com.vti.movie.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LoginDTO {
    public void setUserAgent(String header) {
    }

    public void setToken(String token) {
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginDto {
        private int id;
        private String username;
        private String role;
        private String userAgent; // thông tin trình duyệt đang sử dụng

        private String token;
    }
}
