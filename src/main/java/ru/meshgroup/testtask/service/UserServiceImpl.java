package ru.meshgroup.testtask.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meshgroup.testtask.domain.User;
import ru.meshgroup.testtask.repository.UserRepository;
import ru.meshgroup.testtask.service.iface.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Optional<User> create(User newUser) {
        return Optional.of(newUser)
                .filter(user -> !userRepository.existsByEmail(user.getEmail()))
                .map(userRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Optional<User> update(Long id, User newUser) {
        Optional<User> oldUser = userRepository.findById(id);
        oldUser.ifPresent(updatedUser -> BeanUtils.copyProperties(newUser, updatedUser, "id"));
        return oldUser.map(userRepository::save);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.findById(id)
                .ifPresent(userRepository::delete);
    }
}
