package com.example.ecsite.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 画像ファイルのアップロード・管理を行うサービスクラス。
 */
@Service
public class ImageService {

    @Value("${app.upload-dir:./uploads}")
    private String uploadDir;

    /**
     * ファイルを指定サブディレクトリに保存し、URLパスを返す。
     *
     * @param file   アップロードされたファイル
     * @param subDir 保存先サブディレクトリ（例: "products"）
     * @return 保存されたファイルのURLパス（例: "/uploads/products/xxx.jpg"）
     * @throws IOException ファイル保存失敗時
     */
    public String saveImage(MultipartFile file, String subDir) throws IOException {
        Path dir = Paths.get(uploadDir, subDir);
        Files.createDirectories(dir);

        String originalFilename = file.getOriginalFilename();
        String ext = (originalFilename != null && originalFilename.contains("."))
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : "";
        String filename = UUID.randomUUID().toString() + ext;
        Path filePath = dir.resolve(filename);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return "/uploads/" + subDir + "/" + filename;
    }

    /**
     * 指定されたパスのファイルを削除する。
     *
     * @param imagePath /uploads/... 形式のURLパス
     */
    public void deleteImage(String imagePath) {
        if (imagePath == null || imagePath.isBlank())
            return;
        try {
            String relativePath = imagePath.replaceFirst("^/uploads/", "");
            Path filePath = Paths.get(uploadDir, relativePath);
            Files.deleteIfExists(filePath);
        } catch (IOException ignored) {
        }
    }
}
