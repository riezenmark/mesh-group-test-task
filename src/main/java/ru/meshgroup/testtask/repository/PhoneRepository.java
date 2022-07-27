package ru.meshgroup.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meshgroup.testtask.domain.Phone;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
    boolean existsByValue(String value);
}
