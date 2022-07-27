package ru.meshgroup.testtask.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.Phone;

import java.util.Optional;

public interface PhoneService {
    Optional<Phone> create(Phone newPhone);

    Optional<Phone> getById(Long id);

    Page<Phone> getPage(Pageable pageable);

    Optional<Phone> update(Long id, Phone newPhone);

    void deleteById(Long id);
}
