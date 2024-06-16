package com.gallery.art.server.repository.saved;

import com.gallery.art.server.db.entity.saved.SavedPostEntity;
import com.gallery.art.server.db.entity.saved.SavedPostId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedPostRepository extends JpaRepository<SavedPostEntity, SavedPostId> {}
