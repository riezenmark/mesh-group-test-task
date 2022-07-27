package ru.meshgroup.testtask.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.testtask.model.UserModel;
import ru.meshgroup.testtask.model.view.UserView;
import ru.meshgroup.testtask.service.iface.UserService;
import ru.meshgroup.testtask.mapper.UserMapper;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserView.Response.class)
    public Page<UserModel> list(@PageableDefault Pageable pageable) {
        return userService.getPage(pageable)
                .map(userMapper::mapUserToRestModel);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserView.Response.class)
    public ResponseEntity<UserModel> get(@PathVariable Long id) {
        return userService.getById(id)
                .map(userMapper::mapUserToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserView.Response.class)
    public ResponseEntity<UserModel> add(@Valid @JsonView(UserView.Request.class) @RequestBody UserModel userModel) {
        return Optional.of(userModel)
                .map(userMapper::mapRestModelToUser)
                .flatMap(userService::create)
                .map(userMapper::mapUserToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(UserView.Response.class)
    public ResponseEntity<UserModel> update(
            @PathVariable Long id,
            @Valid @JsonView(UserView.Request.class) @RequestBody UserModel userModel) {
        return Optional.of(userModel)
                .map(userMapper::mapRestModelToUser)
                .flatMap(user -> userService.update(id, user))
                .map(userMapper::mapUserToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
