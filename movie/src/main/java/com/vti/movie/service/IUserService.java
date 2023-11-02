package com.vti.movie.service;

import com.vti.movie.entity.User;
import com.vti.movie.modal.CreateUserRequest;
import com.vti.movie.modal.UpdateUserRequest;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Integer id);
    void deleteUser(Integer id);

    void createUser(CreateUserRequest request);
    User updateUser(int id, UpdateUserRequest request);

}
