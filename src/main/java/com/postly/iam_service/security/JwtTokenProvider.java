package com.postly.iam_service.security;

import com.postly.iam_service.model.entity.Role;
import com.postly.iam_service.model.entity.User;
import com.postly.iam_service.utils.AuthenticationUtils;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final SecretKey jwtSecret;
    private final long jwtExpiration;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration}") long jwtValidityInMilliseconds) {
        this.jwtSecret = getKey(secret);
        this.jwtExpiration = jwtValidityInMilliseconds;
    }

    public String generateToken(@NonNull User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(AuthenticationUtils.USER_ID, user.getId());
        claims.put(AuthenticationUtils.USERNAME, user.getUsername());
        claims.put(AuthenticationUtils.USER_EMAIL, user.getEmail());
        claims.put(AuthenticationUtils.USER_REGISTRATION_STATUS, user.getRegistrationStatus().name());
        claims.put(AuthenticationUtils.LAST_UPDATE, LocalDateTime.now().toString());

        List<String> rolesList = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toList());
        claims.put(AuthenticationUtils.ROLE, rolesList);

        return createToken(claims, user.getEmail());
    }

    public String refreshToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return createToken(claims, claims.getSubject());
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(jwtSecret, SignatureAlgorithm.HS512)
                .compact();
    }

    private SecretKey getKey(String secretKey64) {
        byte[] decode64 = Decoders.BASE64.decode(secretKey64);
        return Keys.hmacShaKeyFor(decode64);
    }

    public String getEmail(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    public List<String> getRoles(String token) {
        return getAllClaimsFromToken(token).get(AuthenticationUtils.ROLE, List.class);
    }

}
