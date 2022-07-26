package ru.meshgroup.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.testtask.domain.Profile;
import ru.meshgroup.testtask.repository.ProfileRepository;
import ru.meshgroup.testtask.service.iface.ProfileService;
import ru.meshgroup.testtask.service.tool.StandardMapper;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final StandardMapper<Profile> mapper;

    @Override
    @Transactional
    public Profile create(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile getById(Long id) {
        return profileRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Profile> getPage(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Profile update(Long id, Profile profile) {
        return profileRepository
                .findById(id)
                .map(profileFromRepo -> mapper.copyFieldsIgnoringId(profile, profileFromRepo))
                .map(profileRepository::save)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        profileRepository
                .findById(id)
                .ifPresent(profileRepository::delete);
    }
}
