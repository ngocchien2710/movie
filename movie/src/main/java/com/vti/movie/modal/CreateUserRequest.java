package com.vti.movie.modal;

import com.vti.movie.entity.ERole;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequest {
    @NotBlank(message = "Tên không được để trống")
    private String username;

    private ERole role;

    @NotBlank(message = "Password không được để tróng")
    @Size(min = 3, max = 6, message = "Password phải có từ 3-6 kí tự")
    private String password;

    @NotBlank(message = "Tên không được để trống")
    private String fullname;

    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @NotBlank(message = "Email không được để trống")
    private String email;


}
