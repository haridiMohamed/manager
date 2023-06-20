package com.project.manager.user.controller;

import com.project.manager.user.entity.User;
import com.project.manager.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    FileController fileController;
    @GetMapping("/users")
    public String display(){

        return "hello word";
    }

    @PostMapping(value="/users/batch", consumes = { "multipart/form-data" } )
    public ResponseEntity<User> saveUser(
             @RequestParam("file") MultipartFile file,@RequestParam("count") Integer count,@RequestParam("firstName") String firstName,
             @RequestParam("lastName") String  lastName,@RequestParam("birthDate") Date  birthDate,@RequestParam("city") String  city,
             @RequestParam("company") String company,@RequestParam("jobPosition") String  jobPosition, @RequestParam("mobile") String  mobile,
             @RequestParam("username") String  username, @RequestParam ("email") String email, @RequestParam("password") String  password,
             @RequestParam("role") String  role) throws IOException {

        String avatar = fileController.uploadFile(file);
        try {
            User saveUser = userRepository
                    .save(new User(count, firstName, lastName, null, city, avatar, company, jobPosition, mobile,
                            username, email, password, role));
                return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
    @GetMapping("/users/generate/{count}")
    public ResponseEntity<User> getUserByCount(@PathVariable("count") long count ){
        Optional<User> userOptional = userRepository.findById(count);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    }
