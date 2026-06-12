package com.example.ecsite.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Value("${app.upload-dir:./uploads}")
    private String uploadDir;

    private final Optional<S3StorageService> s3;

    public ImageService(Optional<S3StorageService> s3) {
        this.s3 = s3;
    }

    public String saveImage(MultipartFile file, String subDir) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String ext = (originalFilename != null && originalFilename.contains("."))
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String filename = UUID.randomUUID().toString() + ext;

        if (s3.isPresent()) {
            String key = subDir + "/" + filename;
            return s3.get().upload(key, file.getInputStream(), file.getSize(),
                    file.getContentType() != null ? file.getContentType() : "application/octet-stream");
        }

        Path dir = Paths.get(uploadDir, subDir);
        Files.createDirectories(dir);
        Path filePath = dir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return "/uploads/" + subDir + "/" + filename;
    }

    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank()) return;

        if (s3.isPresent() && imagePath.startsWith("https://")) {
            // Extract key: everything after the bucket host
            // e.g. https://bucket.s3.region.amazonaws.com/products/file.png → products/file.png
            String baseUrl = s3.get().s3BaseUrl();
            if (imagePath.startsWith(baseUrl + "/")) {
                s3.get().delete(imagePath.substring(baseUrl.length() + 1));
            }
            return;
        }

        try {
            String relativePath = imagePath.replaceFirst("^/uploads/", "");
            Path filePath = Paths.get(uploadDir, relativePath);
            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {
        }
    }
}
