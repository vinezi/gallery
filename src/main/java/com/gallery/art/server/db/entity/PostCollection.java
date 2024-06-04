package com.gallery.art.server.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "post_collection")
@NoArgsConstructor
public class PostCollection extends BaseEntity {

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

}
