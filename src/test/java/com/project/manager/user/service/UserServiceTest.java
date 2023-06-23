package com.project.manager.user.service;

import com.project.manager.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;
    @Test
    void save() {
        User user = new User(10, "firstName", "lastName", null, "city", "avatar", "company", "jobPosition", "mobile",
                "username", "email@gmail.com", encoder.encode("password"), "role");
        User userTest = userService.save(user);
        assertNotNull(userTest);
    }

    @Test
    void findById() {
        Optional<User> user = userService.findById(1);
        user.map(User::getCount).orElse(null);
        assertEquals(1,user.map(User::getCount).orElse(null));
    }

    @Test
    void existsByUsername() {
    }

    @Test
    void existsByEmail() {
    }

    @Test
    void findByUsername() {
    }
}