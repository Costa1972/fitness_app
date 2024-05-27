package ru.costa.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

/**
 * @author Constantin Vashchuk
 */

@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "age")
    private int age;
    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(FetchMode.JOIN)
    private Set<Role> roles;

    @OneToOne
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @RequiredArgsConstructor
    @Getter
    public enum Sex {
        MALE, FEMALE
    }
}
