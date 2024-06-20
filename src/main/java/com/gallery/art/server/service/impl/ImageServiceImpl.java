package com.gallery.art.server.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.gallery.art.server.db.entity.ImageEntity;
import com.gallery.art.server.dto.Image;
import com.gallery.art.server.exeption.ObjectNotExistsException;
import com.gallery.art.server.mapper.ImageMapper;
import com.gallery.art.server.repository.ImageRepository;
import com.gallery.art.server.service.IImageService;
import com.google.common.io.Files;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static org.springframework.http.MediaType.IMAGE_PNG;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    @Value("${custom.pathSave}")
    private String path;
    @Value("${custom.compression}")
    private String compression;
    private final AmazonS3 s3;
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    @Override
    public ResponseEntity<byte[]> loadFile(String bucketName, String fileName) throws IOException {
        objectExistsThrowEx(bucketName, fileName);

        S3Object object = s3.getObject(bucketName, fileName);
        S3ObjectInputStream stream = object.getObjectContent();
        byte[] content = IOUtils.toByteArray(stream);
        object.close();
        Path path = new File(fileName).toPath();

        MediaType mediaType = IMAGE_PNG;
        try {
            mediaType = MediaType.parseMediaType(java.nio.file.Files.probeContentType(path));
        } catch (Exception ex) {
            log.info("Тип не распознан");
        }

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(content);
    }

    @Override
    public Image upload(String bucketName, String name, MultipartFile originalfile) throws IOException {
        if (Files.getFileExtension(name).isEmpty()) name += "." + FilenameUtils.getExtension(originalfile.getOriginalFilename());

        if (!s3.doesBucketExist(bucketName)){
            s3.createBucket(bucketName);
        }

        File file = new File(path + name);
        file.createNewFile();
        file.canWrite();
        file.canRead();
        FileOutputStream iofs = new FileOutputStream(file);
        iofs.write(originalfile.getBytes());

        s3.putObject(bucketName, name, file);

        String fullName = bucketName + '/' + name;
        if (!s3.doesObjectExist(bucketName, name))
            throw new IllegalStateException("Изображение не сохранилось!");

        if (compression == null) {
            return saveImage(fullName, fullName, FilenameUtils.getExtension(originalfile.getOriginalFilename()));
        } else {
            String modifiedFileName = name.replace(".", "-preview.");
            File compressedFile = new File(path + modifiedFileName);
            compressedFile.createNewFile();
            compressedFile.canWrite();
            compressedFile.canRead();
            Thumbnails.of(originalfile.getInputStream()).scale(1 / Double.parseDouble(compression))
                    .outputFormat(FilenameUtils.getExtension(originalfile.getOriginalFilename())).imageType(BufferedImage.TYPE_INT_ARGB).toFile(compressedFile);

            s3.putObject(bucketName, modifiedFileName, compressedFile);

            String fullPreviewFileName = bucketName + '/' + modifiedFileName;
            if (!s3.doesObjectExist(bucketName, modifiedFileName))
                throw new IllegalStateException("Предварительный просмотр изображения не был сохранен!");
            return saveImage(fullPreviewFileName, fullName, FilenameUtils.getExtension(originalfile.getOriginalFilename()));
        }
    }

    @Override
    public void deleteIfExists(String bucketName, String fileName, boolean isAutoDelete) {
        objectExistsThrowEx(bucketName,fileName);
        String fullName =  bucketName + '/' + fileName;
        if (!isAutoDelete)
            imageRepository.deleteByFullFilenameAndPreviewFilename(fullName, fullName);
        s3.deleteObject(bucketName, fileName);
    }

    @Override
    public ImageEntity findImageEntityById(Long id) {
        return imageRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Не было найдено изображение с id: " + id));
    }

    @Override
    public void existImageEntityById(Long id) {
        if (!imageRepository.existsById(id))
            throw new EntityNotFoundException("Не было найдено изображение с id: " + id);
    }

    private void objectExistsThrowEx(String bucketName, String fileName) {
        if (!s3.doesObjectExist(bucketName,fileName))
            throw new ObjectNotExistsException(ObjectNotExistsException.ObjectType.file,
                    "Изображение не найдено по " + bucketName + "/" + fileName + " пути");
    }

    private Image saveImage(String preview, String fullName, String extension) {
        return imageMapper.toDto(imageRepository.save(
                ImageEntity.builder()
                        .previewFilename(preview)
                        .fullFilename(fullName)
                        .fileExtension(extension)
                        .build()
        ));
    }


}
