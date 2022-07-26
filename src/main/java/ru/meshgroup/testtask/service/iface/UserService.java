package ru.meshgroup.testtask.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.User;

public interface UserService {
    User create(User user);

    User getById(Long id);

    Page<User> getPage(Pageable pageable);

    User update(Long id, User user);

    void deleteById(Long id);
}
