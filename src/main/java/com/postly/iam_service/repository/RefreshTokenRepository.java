package com.postly.iam_service.repository;

import com.postly.iam_service.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByUserId(Integer userId);

    Optional<RefreshToken> findByToken(String token);

}
