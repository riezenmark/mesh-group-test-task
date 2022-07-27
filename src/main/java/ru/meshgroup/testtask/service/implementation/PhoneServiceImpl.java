package ru.meshgroup.testtask.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.testtask.domain.Phone;
import ru.meshgroup.testtask.repository.PhoneRepository;
import ru.meshgroup.testtask.service.PhoneService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    private final PhoneRepository phoneRepository;

    @Override
    @Transactional
    public Optional<Phone> create(Phone newPhone) {
        return Optional.of(newPhone)
                .filter(phone -> !phoneRepository.existsByValue(newPhone.getValue()))
                .map(phoneRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Phone> getById(Long id) {
        return phoneRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Phone> getPage(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<Phone> update(Long id, Phone newPhone) {
        Optional<Phone> oldPhone = phoneRepository.findById(id);
        oldPhone.ifPresent(updatedPhone -> BeanUtils.copyProperties(newPhone, updatedPhone, "id"));
        return oldPhone.map(phoneRepository::save);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        phoneRepository.findById(id)
                .ifPresent(phoneRepository::delete);
    }
}
