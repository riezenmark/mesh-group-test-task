package ru.meshgroup.testtask.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.Profile;

public interface ProfileService {
    Profile create(Profile user);

    Profile getById(Long id);

    Page<Profile> getPage(Pageable pageable);

    Profile update(Long id, Profile profile);

    void deleteById(Long id);
}
