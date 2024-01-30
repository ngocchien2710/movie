package com.vti.movie.modal.request;

import com.vti.movie.modal.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
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
