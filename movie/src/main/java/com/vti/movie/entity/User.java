package com.vti.movie.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Data
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @Column(name="username", length = 100, unique = true, nullable = false)
    private String username;

    @Column(name= "`password`", length = 100 ,nullable = false)
    private String password;

    @Column(name="fullname", length = 100, nullable = false)
    private String fullName;

    @Column(name="phone_number", length = (100),nullable = false)
    private String phoneNumber;

    @Column(name="email", length = 100, nullable = false)
    private String email;

    @Column(name = "`role`")
    @Enumerated(EnumType.STRING)
    private ERole role;



//
//    public User(String username, String password, List<GrantedAuthority> authorities) {
//    }
}
