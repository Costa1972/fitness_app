package ru.costa.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.costa.backend.entities.Passport;

import java.util.Optional;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Long> {


    Optional<Passport> findById(Long passportId);
    Passport findBySeries(String series);
    Passport findByDateOfIssue(String dateOfIssue);
}
