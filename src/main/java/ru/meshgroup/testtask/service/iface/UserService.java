package ru.meshgroup.testtask.service.iface;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meshgroup.testtask.domain.User;
import ru.meshgroup.testtask.model.UserModel;

import java.util.Optional;

public interface UserService {
    Optional<User> create(User newUser);

    Optional<User> getById(Long id);

    Page<User> getPage(Pageable pageable);

    Optional<User> update(Long id, User newUser);

    void deleteById(Long id);
}
