package com.project.manager.user.controller;

import com.project.manager.user.entity.User;
import com.project.manager.user.repository.UserRepository;
import com.project.manager.user.service.FileService;
import com.project.manager.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FileService fileService;
    @Autowired
    FileController fileController;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/users/generate")
    public ResponseEntity<User> getUserByCount(@RequestParam("count") long count) {
        Optional<User> userOptional = userService.findById(count);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/users/batch", consumes = {"multipart/form-data"})
    public ResponseEntity<?> saveUser(@Valid
            @RequestParam("file") MultipartFile file, @RequestParam("count") Integer count, @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName, @RequestParam("city") String city, @RequestParam("birthDate") @DateTimeFormat(pattern = "dd/MMM/yyyy") Date birthDate,
                                      @RequestParam("company") String company, @RequestParam("jobPosition") String jobPosition, @RequestParam("mobile") String mobile,
                                      @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password,
                                      @RequestParam("role") String role)
    {
       if(userService.existsByUsername(username) ){
           return new ResponseEntity<>("Ce nom d'utilisateur est deja utijise", HttpStatus.CONFLICT);
       }if(userService.existsByEmail(email)){
        return new ResponseEntity<>("Ce email est deja utijise", HttpStatus.CONFLICT);
      }
        String avatar = fileService.uploadFile(file);
        try {
            User user = new User(count, firstName, lastName, birthDate, city, avatar, company, jobPosition, mobile,
                            username, email, encoder.encode(password), role);
            User saveUser = userService.save(user);
            return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/me")
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public  ResponseEntity<?>userProfile(Authentication authentication) {
        try {
            String authenticatedUsername = authentication.getName();
            Optional<User> userOptional = userService.findByUsername(authenticatedUsername);
            return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } catch (Exception e) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("authentication.principal.username == #username || hasAuthority('ADMIN')")
    public ResponseEntity<User> getUserByUsername(Authentication authentication, @PathVariable("username") String username) {
        String authenticatedUsername = authentication.getName();
        if (authenticatedUsername.equals(username) || authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            Optional<User> userOptional = userService.findByUsername(username);
            return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
