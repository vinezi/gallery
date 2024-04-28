package com.gallery.art.server.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Setter
@Getter
@Entity
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity extends BaseEntity {

    @Column(name = "preview_filename")
    private String previewFilename;

    @Column(name = "full_filename")
    private String fullFilename;

    @Column(name = "file_extension")
    private String fileExtension;
}
