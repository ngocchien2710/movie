package com.vti.movie.controllers;

import com.vti.movie.entity.User;
import com.vti.movie.modal.CreateUserRequest;
import com.vti.movie.modal.UpdateUserRequest;
import com.vti.movie.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("get-all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest request){
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable int id){
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/update/id")
    public void updateUser(@RequestBody UpdateUserRequest request ,@PathVariable int id){
        userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
}
