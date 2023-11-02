package com.vti.movie.modal;

import com.vti.movie.entity.ERole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Tên không được để trống")
    private String username;

    private ERole role;

    @NotBlank(message = "Password không được để tróng")
    @Size(min = 3, max = 6, message = "Password phải có từ 3-6 kí tự")
    private String password;

    private String fullname;

    private String phoneNumber;

    private String email;


}
