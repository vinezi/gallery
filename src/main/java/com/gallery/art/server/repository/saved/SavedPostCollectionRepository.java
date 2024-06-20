package com.gallery.art.server.repository.saved;

import com.gallery.art.server.db.entity.saved.SavedCollectionEntity;
import com.gallery.art.server.db.entity.saved.SavedCollectionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavedPostCollectionRepository extends JpaRepository<SavedCollectionEntity, SavedCollectionId> {}
