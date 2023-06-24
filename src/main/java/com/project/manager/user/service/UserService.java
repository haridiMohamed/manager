package com.project.manager.user.service;

import com.project.manager.user.entity.User;
import com.project.manager.user.repository.UserRepository;
import com.project.manager.user.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
           return userRepository.save(user);
    }
    @Override
    public Optional<User>  findById(long count) {
       return userRepository.findById(count);
    }
    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
