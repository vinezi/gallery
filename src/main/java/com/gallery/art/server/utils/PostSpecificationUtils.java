package com.gallery.art.server.utils;

import com.gallery.art.server.db.entity.PostCollectionEntity;
import com.gallery.art.server.db.entity.PostEntity;
import com.gallery.art.server.filters.post.FilterPostRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public class PostSpecificationUtils {

    public static Specification<PostEntity> postEntitySpecificationForFilter(FilterPostRequest filterPostRequest) {
        Set<Specification<PostEntity>> specifications = new HashSet<>();
        Boolean isSaved = filterPostRequest.saved() != null ? filterPostRequest.saved() : false;

        if (filterPostRequest.userId() != null) {
            if (isSaved) {
                specifications.add(getContainsSavedSpecification(filterPostRequest.userId()));
            } else {
                specifications.add(getContainsOwnerSpecification(filterPostRequest.userId()));
            }
        }

        Specification<PostEntity> result = null;
        for (Specification<PostEntity> spec : specifications) {
            if (result == null) {
                result = spec;
            } else {
                result = result.and(spec);
            }
        }
        return result;
    }

    public static Specification<PostCollectionEntity> postCollectionEntitySpecificationForFilter(FilterPostRequest filterPostRequest) {
        Set<Specification<PostCollectionEntity>> specifications = new HashSet<>();
        Boolean isSaved = filterPostRequest.saved() != null ? filterPostRequest.saved() : false;

        if (filterPostRequest.userId() != null) {
            if (isSaved) {
                specifications.add(getContainsOwnerCollectionSpecification(filterPostRequest.userId()));
            } else {
                specifications.add(getContainsSavedCollectionSpecification(filterPostRequest.userId()));
            }
        }

        Specification<PostCollectionEntity> result = null;
        for (Specification<PostCollectionEntity> spec : specifications) {
            if (result == null) {
                result = spec;
            } else {
                result = result.and(spec);
            }
        }
        return result;
    }

    private static Specification<PostEntity> getContainsOwnerSpecification(Long value) {
        return new Specification<>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("owner").get("id"), value);
            }
        };
    }

    private static Specification<PostEntity> getContainsSavedSpecification(Long value) {
        return new Specification<>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<PostEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.join("savedByUser").get("user").get("id"), value);
            }
        };
    }

    private static Specification<PostCollectionEntity> getContainsOwnerCollectionSpecification(Long value) {
        return new Specification<>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<PostCollectionEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("owner").get("id"), value);
            }
        };
    }

    private static Specification<PostCollectionEntity> getContainsSavedCollectionSpecification(Long value) {
        return new Specification<>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<PostCollectionEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.join("savedByUser").get("user").get("id"), value);
            }
        };
    }

}
