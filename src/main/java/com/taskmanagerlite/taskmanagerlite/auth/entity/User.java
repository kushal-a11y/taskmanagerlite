package com.taskmanagerlite.taskmanagerlite.auth.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import static java.util.Locale.ROOT;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(name = "uk_email_constaint", columnNames = "email") })
public class User {
    /*
     * The User entity should contain:
     * - id — generated primary key
     * - email — required and unique
     * - passwordHash — store only the encoded password
     * - role — USER or ADMIN
     * - createdAt — creation timestamp
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // unique = true
    @Column(nullable = false, length = 254)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role = Role.USER;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    private User(String email, String passwordHash, Role role) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (passwordHash == null || passwordHash.isBlank()) {
            throw new IllegalArgumentException("Password hash cannot be null or blank");
        }

        this.email = email.trim().toLowerCase(ROOT);
        this.passwordHash = passwordHash;
        this.role = (role == null) ? Role.USER : role; // Safe default fallback
    }
}
