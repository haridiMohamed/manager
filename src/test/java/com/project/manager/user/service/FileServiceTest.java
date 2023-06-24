package com.project.manager.user.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceTest {
    @Autowired
    FileService fileService;
    @Test
    void uploadFile() {
    }

    @Test
    void displayFile() throws IOException {

      //  ResponseEntity<byte[]>  response = fileService.displayFile("uploads/user.jpeg");
     //   assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}