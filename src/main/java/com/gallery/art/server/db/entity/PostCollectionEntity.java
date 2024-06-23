package com.gallery.art.server.db.entity;

import com.gallery.art.server.db.entity.collection.PostInCollectionEntity;
import com.gallery.art.server.db.entity.saved.SavedCollectionEntity;
import com.gallery.art.server.db.listener.PostCollectionEntityListener;
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
@Table(name = "post_collection")
@EntityListeners(PostCollectionEntityListener.class)
@NoArgsConstructor
public class PostCollectionEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "collection")
    private Set<PostInCollectionEntity> posts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private OffsetDateTime createdDate;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean deleted = false;

    @Column(columnDefinition = "BOOLEAN DEFAULT FALSE", nullable = false)
    private boolean hide = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "postCollection")
    private Set<SavedCollectionEntity> savedByUser = new HashSet<>();

    @Transient
    private boolean saved;
}
