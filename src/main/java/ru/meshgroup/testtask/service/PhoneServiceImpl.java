package ru.meshgroup.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.testtask.domain.Phone;
import ru.meshgroup.testtask.repository.PhoneRepository;
import ru.meshgroup.testtask.service.iface.PhoneService;
import ru.meshgroup.testtask.service.tool.StandardMapper;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;
    private final StandardMapper<Phone> mapper;

    @Override
    @Transactional
    public Phone create(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    @Transactional(readOnly = true)
    public Phone getById(Long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Phone> getPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Phone update(Long id, Phone phone) {
        return phoneRepository
                .findById(id)
                .map(profileFromRepo -> mapper.copyFieldsIgnoringId(phone, profileFromRepo))
                .map(phoneRepository::save)
                .orElse(null);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        phoneRepository
                .findById(id)
                .ifPresent(phoneRepository::delete);
    }
}
