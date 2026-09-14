package com.postly.iam_service.repository.criteria;

import com.postly.iam_service.model.entity.Post;
import com.postly.iam_service.model.request.post.SearchPostRequest;
import com.postly.iam_service.utils.PostUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class PostSearchCriteria implements Specification<Post> {

    private final SearchPostRequest request;

    @Override
    public Predicate toPredicate(
            @NonNull Root<Post> root,
            CriteriaQuery<?> query,
            @NonNull CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(request.getTitle())) {
            predicates.add(criteriaBuilder.like(root.get(PostUtils.TITLE_FIELD_NAME), "%" + request.getTitle() + "%"));
        }

        if (Objects.nonNull(request.getContent())) {
            predicates.add(criteriaBuilder.like(root.get(PostUtils.CONTENT_FIELD_NAME), "%" + request.getContent() + "%"));
        }

        if (Objects.nonNull(request.getLikes())) {
            predicates.add(criteriaBuilder.equal(root.get(PostUtils.LIKES_FIELD_NAME), request.getLikes()));
        }

        if (Objects.nonNull(request.getDeleted())) {
            predicates.add(criteriaBuilder.equal(root.get(PostUtils.DELETED_FIELD_NAME), request.getDeleted()));
        }

        if (Objects.nonNull(request.getKeyword())) {
            Predicate keywordPredicate = criteriaBuilder.or(
                    criteriaBuilder.like(root.get(PostUtils.TITLE_FIELD_NAME), "%" + request.getTitle() + "%"),
                    criteriaBuilder.like(root.get(PostUtils.CONTENT_FIELD_NAME), "%" + request.getContent() + "%")
            );
            predicates.add(keywordPredicate);
        }

        sort(root, query, criteriaBuilder);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void sort(@NonNull Root<Post> root, CriteriaQuery<?> query, @NonNull CriteriaBuilder criteriaBuilder) {
        if (Objects.nonNull(request.getPostSortField())) {
            switch (request.getPostSortField()) {
                case TITLE -> query.orderBy(criteriaBuilder.desc(root.get(PostUtils.TITLE_FIELD_NAME)));
                case CONTENT -> query.orderBy(criteriaBuilder.desc(root.get(PostUtils.CONTENT_FIELD_NAME)));
                case LIKES -> query.orderBy(criteriaBuilder.desc(root.get(PostUtils.LIKES_FIELD_NAME)));
                default -> query.orderBy(criteriaBuilder.desc(root.get(PostUtils.ID_FIELD_NAME)));
            }
        }  else {
            query.orderBy(criteriaBuilder.desc(root.get(PostUtils.ID_FIELD_NAME)));
        }
    }
}
