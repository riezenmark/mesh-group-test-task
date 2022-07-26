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
import ru.meshgroup.testtask.service.tool.StandardMapper;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final StandardMapper<User> mapper;

    @Override
    @Transactional
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public User update(Long id, User user) {
        return userRepository
                .findById(id)
                .map(userFromRepo -> mapper.copyFieldsIgnoringId(user, userFromRepo))
                .map(userRepository::save)
                .orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        userRepository
                .findById(id)
                .ifPresent(userRepository::delete);
    }
}
