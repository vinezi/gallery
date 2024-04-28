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
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    @Value("${custom.pathSave}")
    private String path;
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
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(java.nio.file.Files.probeContentType(path)))
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

        return imageMapper.toDto(imageRepository.save(
                ImageEntity.builder()
                        .previewFilename(fullName)
                        .fullFilename(fullName)
                        .fileExtension(FilenameUtils.getExtension(originalfile.getOriginalFilename()))
                        .build()
        ));
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

    private void objectExistsThrowEx(String bucketName, String fileName) {
        if (!s3.doesObjectExist(bucketName,fileName))
            throw new ObjectNotExistsException(ObjectNotExistsException.ObjectType.file,
                    "Изображение не найдено по " + bucketName + "/" + fileName + " пути");
    }


}
