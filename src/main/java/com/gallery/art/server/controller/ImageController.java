package com.gallery.art.server.controller;

import com.gallery.art.server.dto.Image;
import com.gallery.art.server.service.IImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Tag(name = "Image API")
@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {

    private final IImageService imageService;

    @Operation(summary = "Выкачка изображения по пути")
    @GetMapping(value = "/{bucketName}/{keyName}")
    public @ResponseBody ResponseEntity<byte[]> getFile(@PathVariable("bucketName") String bucketName, @PathVariable("keyName") String keyName) throws Exception {
        return imageService.loadFile(bucketName, keyName);
    }

    @Validated
    @Operation(summary = "Загрузка изображения")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Image> uploadImage(@RequestPart("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(imageService.upload("default", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), file));
    }

    @Operation(summary = "Удаление изображения")
    @DeleteMapping(value = "/{bucketName}/{keyName}")
    public ResponseEntity<String> deleteImage(@PathVariable("bucketName") String bucketName, @PathVariable("keyName") String keyName) {
        imageService.deleteIfExists(bucketName, keyName, false);
        return ResponseEntity.ok("file " + bucketName + "/" + keyName + " deleted");
    }
}
