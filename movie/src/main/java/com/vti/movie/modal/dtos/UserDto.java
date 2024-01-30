package com.vti.movie.modal.dtos;

import com.vti.movie.modal.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;

    private ERole role;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String cccd;

    private String address;

    private String phoneNumber;

    private String bankCardNumber;

    private String username;

    private String password;
}
