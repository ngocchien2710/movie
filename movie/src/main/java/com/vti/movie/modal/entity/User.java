package com.vti.movie.modal.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole role;

    @Column(name = "first_name",length = 50)
    private String firstName;

    @Column(name = "last_name",length = 50)
    private String lastName;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "cccd",length = 50, unique = true)
    private String cccd;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "phone_number",length = 20, unique = true)
    private String phoneNumber;

    @Column(name = "bank_card_number",length = 20, unique = true)
    private String bankCardNumber;

    @Column(name = "username",length = 50,unique = true)
    private String username;

    @Column(name = "password",length = 50,unique = true)
    private String password;



}


//
//    public User(String username, String password, List<GrantedAuthority> authorities) {
//    }

