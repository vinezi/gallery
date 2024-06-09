package com.gallery.art.server.service;

import com.gallery.art.server.db.entity.ImageEntity;
import com.gallery.art.server.dto.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {

    ResponseEntity<byte[]> loadFile(String bucketName, String fileName) throws IOException;

    Image upload(String bucketName, String name, MultipartFile originalfile) throws IOException;

    void deleteIfExists(String bucketName, String fileName, boolean isAutoDelete);

    ImageEntity findImageEntityById(Long id);

    void existImageEntityById(Long id);
}
