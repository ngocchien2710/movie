package com.vti.movie.controllers;

import com.vti.movie.modal.entity.User;
import com.vti.movie.modal.request.CreateUserRequest;
import com.vti.movie.modal.request.UpdateUserRequest;


import com.vti.movie.service.implement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/get-all")
    public List<User> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        return service.getById(id);
    }

    @PostMapping("/create")
    public void create(@RequestBody CreateUserRequest request){

        service.create(request);
    }

    @PutMapping("/update")
    public User update(@RequestBody UpdateUserRequest request){
        return service.update(request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id){
        service.delete(id);
    }
}