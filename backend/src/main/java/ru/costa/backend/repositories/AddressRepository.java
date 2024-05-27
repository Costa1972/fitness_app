package ru.costa.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.costa.backend.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
