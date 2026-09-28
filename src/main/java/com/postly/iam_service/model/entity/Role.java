package com.postly.iam_service.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_system_role")
    private UserSystemRole userSystemRole;

    private Boolean active = true;

    @Column(name = "created_by")
    private String createdBy;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles", cascade = CascadeType.MERGE)
    private Set<User> users = new HashSet<>();

    @Override
    public @Nullable String getAuthority() {
        return userSystemRole.name();
    }
}
