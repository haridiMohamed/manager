package com.project.manager.user.controller;

import com.project.manager.user.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Access;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api")
public class FileController {
    @Autowired
    FileService fileService;
    @GetMapping("/uploads/{fileName:.+}")
    public ResponseEntity<byte[]> displayImage(@PathVariable String fileName) throws IOException {
        String filePath = "uploads/" + fileName;
        return fileService.displayFile(filePath);
    }

}
