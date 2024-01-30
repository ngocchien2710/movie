package com.vti.movie.modal.request;

import com.vti.movie.modal.entity.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Tên không được để trống")
    private String username;

    private ERole role;

    @NotBlank(message = "Tên không được để trống")
    private String lastname;


    @NotBlank(message = "Tên không được để trống")
    private String firstname;

    @NotBlank(message = "Password không được để tróng")
    @Size(min = 3, max = 6, message = "Password phải có từ 3-6 kí tự")
    private String password;


    @NotBlank(message = "Số điện thoại không được để trống")
    private String phoneNumber;

    @NotBlank(message = "Địa chỉ không được để trống")
    private String address;

    @NotBlank(message = "Không được để trống")
    private String cccd;

    @NotBlank(message = "Không được để trống")
    private String bankCardNumber;

    @NotBlank(message = "Không được để trống")
    private Date dateOfBirth;


}
