package ru.meshgroup.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.testtask.domain.Profile;
import ru.meshgroup.testtask.repository.ProfileRepository;
import ru.meshgroup.testtask.service.iface.ProfileService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    @Transactional
    public Optional<Profile> create(Profile newProfile) {
        Profile savedProfile = profileRepository.save(newProfile);
        return Optional.of(savedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> getById(Long id) {
        return profileRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Profile> getPage(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Profile> update(Long id, Profile newProfile) {
        Optional<Profile> oldProfile = profileRepository.findById(id);
        oldProfile.ifPresent(updatedProfile -> BeanUtils.copyProperties(newProfile, updatedProfile, "id"));
        return oldProfile.map(profileRepository::save);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        profileRepository.findById(id)
                .ifPresent(profileRepository::delete);
    }
}
