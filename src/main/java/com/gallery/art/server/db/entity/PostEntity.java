package com.gallery.art.server.db.entity;

import com.gallery.art.server.db.entity.saved.SavedPostEntity;
import com.gallery.art.server.db.listener.PostEntityListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "posts")
@NoArgsConstructor
@EntityListeners(PostEntityListener.class)
public class PostEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_image",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<ImageEntity> images = new HashSet<>();

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean deleted = false;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private OffsetDateTime createdDate;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean hide = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post")
    private Set<SavedPostEntity> savedByUser;

    @Transient
    private boolean saved;
}
