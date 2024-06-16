package com.gallery.art.server.db.entity.saved;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "saved_post_collections")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SavedCollectionId.class)
public class SavedCollectionEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_collection_id")
    private PostCollectionEntity postCollection;

}
