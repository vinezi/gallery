package com.gallery.art.server.repository;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCollectionRepository extends JpaRepository<PostCollectionEntity, Long>  {
    Page<PostCollectionEntity> findAll(Specification<PostCollectionEntity> specification, Pageable page);
}
