package com.vti.movie.entity;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    ADMIN,
    CUSTOMER;

    @Override
    public String getAuthority(){
        return null;
    }
}
