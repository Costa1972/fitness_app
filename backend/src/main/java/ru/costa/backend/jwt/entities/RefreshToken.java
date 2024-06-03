package ru.costa.backend.jwt.entities;

import jakarta.persistence.*;
import lombok.Data;
import ru.costa.backend.entities.User;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@Data
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "token_id", referencedColumnName = "user_id")
    private User user;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Instant expiryDate;
}
