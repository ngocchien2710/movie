package com.vti.movie.service;

import com.vti.movie.entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    void remove(Integer id);
    Optional<User> findByUsername(String username);
}
