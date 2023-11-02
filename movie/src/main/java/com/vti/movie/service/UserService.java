package com.vti.movie.service;

import com.vti.movie.entity.User;
import com.vti.movie.exception.AppException;
import com.vti.movie.exception.ErrorResponseBase;
import com.vti.movie.modal.CreateUserRequest;
import com.vti.movie.modal.UpdateUserRequest;
import com.vti.movie.repository.IUserRepository;
import com.vti.movie.security.UserDetailsService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService , UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUserName(String username) throws UsernameNotFoundException {
        Optional<User> optional = userRepository.findByUsername(username);
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
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Integer id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()){
            throw new AppException(ErrorResponseBase.NOT_FOUND);
        } try{
            return optional.get();
        }  catch (Exception exception){
            //dù có lỗi gì xảy ra thì throw ra 1 đối tượng app exception
            throw new AppException(exception);
        }

    }

    @Override
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void createUser(CreateUserRequest request) {
        User user =  new User();
        String encodePassword = encoder.encode(request.getPassword()); //lấy ra mã hóa password
        BeanUtils.copyProperties(request, user);
        user.setPassword(encodePassword); // set password ở db chính là dãy mã hóa
        //Kiếm tra username đã tồn tại chưa
        Optional<User> userCheck = userRepository.findByUsername(request.getUsername());
        if (userCheck.isPresent()) {
            //username đã tồn tại thì bắn ra lỗi
        throw  new AppException(ErrorResponseBase.USERNAME_EXISTED);
        }
    }

    @Override
    public User updateUser(int id, UpdateUserRequest request) {
        User userDB = findById(id);
        if (userDB != null) {
           BeanUtils.copyProperties(request, userDB);
           userDB.setId(id);
        }
        return userRepository.save(userDB);
    }
}
