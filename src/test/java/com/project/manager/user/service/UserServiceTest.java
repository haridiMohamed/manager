package com.project.manager.user.service;

import com.project.manager.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;
    @Test
    void save() throws ParseException {
        String dateInString = "27/07/2021";
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date birthDate = format.parse(dateInString);
        User user = new User(10L, "firstName", "lastName", birthDate, "city", "avatar", "company", "jobPosition", "mobile",
                "username", "email@gmail.com", encoder.encode("1234567890"), "role");
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
        Boolean checkUser = userService.existsByUsername("simoniS");
        assertEquals(false,checkUser);
    }

    @Test
    void existsByEmail() {
        Boolean checkUser = userService.existsByEmail("simoni@gmail.com");
        assertEquals(false,checkUser);
    }

    @Test
    void findByUsername() {
        Optional<User> checkUser = userService.findByUsername("simoni");
        assertNotNull(checkUser);
    }
}