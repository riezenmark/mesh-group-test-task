package ru.meshgroup.testtask.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.Profile;

import java.util.Optional;

public interface ProfileService {
    Optional<Profile> create(Profile newProfile);

    Optional<Profile> getById(Long id);

    Page<Profile> getPage(Pageable pageable);

    Optional<Profile> update(Long id, Profile newProfile);

    void deleteById(Long id);
}
