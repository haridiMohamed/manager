package com.project.manager.user.controller;

import com.project.manager.user.Constants;
import com.project.manager.user.entity.AvatarFile;
import com.project.manager.user.entity.User;
import com.project.manager.user.repository.AvatarFileRepository;
import com.project.manager.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;
    AvatarFileRepository avatarFileRepository;
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
             @RequestParam("role") String  role) {
        try {
            String avatar = uploadFile(file);
            User saveUser = userRepository
                    .save(new User(count, firstName, lastName, birthDate, city, avatar, company, jobPosition, mobile,
                            username, email, password, role));
                return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

}
    private String uploadFile(MultipartFile file) throws IOException {
            AvatarFile avatarFile = new AvatarFile();
            byte[] bytes= file.getBytes();
            Path path = Paths.get("upload-dir"+ File.separator+ Constants.relativePath+File.separator+file.getOriginalFilename());
            Files.write(path, bytes);
            avatarFile.setUrl("localhost:9090/api/users/"+file.getOriginalFilename());
            avatarFile.setName(file.getOriginalFilename());
            avatarFileRepository.save(avatarFile);
            return avatarFile.getUrl();
    }
    }
