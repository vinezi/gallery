package com.gallery.art.server.db.entity.collection;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.PostEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "post_post_collection")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(PostInCollectionId.class)
public class PostInCollectionEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_collection_id")
    private PostCollectionEntity collection;
}
