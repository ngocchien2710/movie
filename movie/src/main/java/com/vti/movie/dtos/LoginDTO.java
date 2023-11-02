package com.vti.movie.dtos;

import com.vti.movie.entity.ERole;
import lombok.*;

@Getter
@Setter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class LoginDTO {
        private int id;
        private String username;
        private ERole role;
        private String userAgent; // thông tin trình duyệt đang sử dụng

        private String token;
    }

