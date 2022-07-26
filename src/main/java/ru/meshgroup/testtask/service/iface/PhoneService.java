package ru.meshgroup.testtask.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.Phone;

public interface PhoneService {
    Phone create(Phone user);

    Phone getById(Long id);

    Page<Phone> getPage(Pageable pageable);

    Phone update(Long id, Phone phone);

    void deleteById(Long id);
}
