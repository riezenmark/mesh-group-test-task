package ru.meshgroup.testtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meshgroup.testtask.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
