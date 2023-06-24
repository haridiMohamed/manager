package com.project.manager.user.service.interfaces;

import com.project.manager.user.entity.User;

import java.util.Optional;

public interface IUserService {

    User save(User user);
    Optional<User> findById(long count);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}
