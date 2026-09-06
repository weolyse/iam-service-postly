package com.postly.iam_service.repository;

import com.postly.iam_service.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    boolean existsByTitle(String title);

}
