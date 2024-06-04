package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.PostCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCollectionRepository extends JpaRepository<PostCollection, Long>  {
}
