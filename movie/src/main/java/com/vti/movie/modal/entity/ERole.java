package com.vti.movie.modal.entity;

import org.springframework.security.core.GrantedAuthority;

public enum ERole implements GrantedAuthority {
    ADMIN,
    USER;

    @Override
    public String getAuthority(){
        return null;
    }
}
