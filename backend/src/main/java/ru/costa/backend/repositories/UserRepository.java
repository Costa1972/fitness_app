package ru.costa.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.costa.backend.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
    User findByLastName(String lastName);
    User findByAge(int age);
    User findByEmailAndLastName(String email, String lastName);
    User findBySex(String sex);
}
