package com.gallery.art.server.db.entity;

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
@NoArgsConstructor
public class PostCollectionEntity extends BaseEntity {

    @Column
    private String title;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "post_post_collection",
            joinColumns = @JoinColumn(name = "post_collection_id"),
            inverseJoinColumns = @JoinColumn(name = "post_id")
    )
    private Set<PostEntity> posts = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @Column(name = "created_date", updatable = false)
    @CreatedDate
    private OffsetDateTime createdDate;

    @Column(columnDefinition = "false", nullable = false)
    private boolean deleted = false;

    @Column(columnDefinition = "false", nullable = false)
    private boolean hide = false;
}
