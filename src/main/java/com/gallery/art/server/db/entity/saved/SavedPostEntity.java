package com.gallery.art.server.db.entity.saved;

import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.db.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "saved_post")
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SavedPostId.class)
public class SavedPostEntity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}
