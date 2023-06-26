package com.project.manager.user.service;

import com.project.manager.user.entity.User;
import com.project.manager.user.repository.UserRepository;
import com.project.manager.user.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
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

    public static String generateRandomPassword() {
        int minLength = 6;
        int maxLength = 10;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        int length = (int) (Math.random() * (maxLength - minLength + 1) + minLength);

        for (int i = 0; i < length; i++) {
            int randomIndex = (int) (Math.random() * characters.length());
            password.append(characters.charAt(randomIndex));
        }
        System.out.println("Generate password = "+ password);
        return password.toString();
    }
}
