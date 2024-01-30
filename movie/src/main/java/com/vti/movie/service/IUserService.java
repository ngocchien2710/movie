package com.vti.movie.service;

import com.vti.movie.modal.entity.User;
import com.vti.movie.modal.request.CreateUserRequest;
import com.vti.movie.modal.request.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface IUserService {
    List<User> getAll();

    User getById(int id);

    void create(CreateUserRequest request);

    User update(UpdateUserRequest request);

    void delete(int id);

    UserDetails loadUserByUserName(String username) throws UsernameNotFoundException;
}
