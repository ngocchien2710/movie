package com.vti.movie.service.implement;


import com.vti.movie.modal.entity.ERole;
import com.vti.movie.modal.entity.User;
import com.vti.movie.modal.request.CreateUserRequest;
import com.vti.movie.modal.request.UpdateUserRequest;
import com.vti.movie.repository.IUserRepository;
import com.vti.movie.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService, UserDetailsService {
    @Autowired
    private IUserRepository repository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public List<User> getAll() {

        return repository.findAll();
    }

    @Override
    public User getById(int id) {
        Optional<User> optional = repository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }



    @Override
    public void create(CreateUserRequest request) {
        User bUser = new User();

        BeanUtils.copyProperties(request, bUser);

        bUser.setRole(ERole.USER);
        repository.save(bUser);



    }

    @Override
    public User update(UpdateUserRequest request) {
        Optional<User> optionalUser = repository.findById(request.getId());
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            BeanUtils.copyProperties(request,user);
            return repository.save(user);
        }
        return null;
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        Optional<User> optional = repository.findByUsername(username);
        if(optional.isPresent()){
            User user = optional.get();
            //Lay ra gia tri phan quyen
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(user.getRole());

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }else {
            throw  new UsernameNotFoundException(username);
        }

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

