package com.example.pathfind.service.impl;

import com.example.pathfind.model.binding.UserRegisterBindingModel;
import com.example.pathfind.model.entity.User;
import com.example.pathfind.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegisterBindingModel registration){
        if(!registration.getPassword().equals(registration.getConfirmPassword())){
            throw new RuntimeException("password.match");
        }
        Optional<User> byEmail = this.userRepository.findByEmail(registration.getEmail());

        if(byEmail.isPresent()){
            throw new RuntimeException("email.used");
        }

        User user = new User(
                registration.getUsername(),
                registration.getPassword(),
                registration.getEmail(),
                registration.getFullName(),
                registration.getAge()
        );

        userRepository.save(user);
    }
}
