package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.collection.PostInCollectionEntity;
import com.gallery.art.server.db.entity.collection.PostInCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostInCollectionRepository extends JpaRepository<PostInCollectionEntity, PostInCollectionId> {}
