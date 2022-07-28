package ru.meshgroup.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meshgroup.testtask.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndIdNot(String email, Long id);
}
