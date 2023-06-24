package com.project.manager.user.service.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    String uploadFile(MultipartFile file);
    ResponseEntity<byte[]> displayFile(String filePath) throws IOException;
}
