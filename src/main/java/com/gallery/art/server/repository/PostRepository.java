package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {

//    @Query("SELECT p " +
//            "FROM PostEntity p " +
//            "WHERE similarity(p.name, :searchString) > 0.1 " +
//            "ORDER BY similarity(p.name, :searchString) DESC")
//    Page<PostEntity> findBySimilarity(@Param("searchString") String searchString, Pageable pageable);

    Page<PostEntity> findAll(Specification<PostEntity> specification, Pageable page);
}
