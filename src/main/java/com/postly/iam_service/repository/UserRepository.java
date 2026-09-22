package com.postly.iam_service.repository;

import com.postly.iam_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByIdAndDeletedFalse(Integer id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<User> findByEmailAndDeletedFalse(String email);

    Optional<User> findByEmail(String email);

}
