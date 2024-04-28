package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    void deleteByFullFilenameAndPreviewFilename(String fullFilename, String previewFilename);
}
