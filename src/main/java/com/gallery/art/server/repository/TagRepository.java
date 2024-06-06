package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {

    @Query("SELECT t " +
            "FROM TagEntity t " +
            "WHERE similarity(t.name, :searchString) > 0.1 " +
            "ORDER BY similarity(t.name, :searchString) DESC")
    Page<TagEntity> findBySimilarity(String searchString, Pageable pageable);
}
