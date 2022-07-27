package ru.meshgroup.testtask.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.testtask.mapper.ProfileMapper;
import ru.meshgroup.testtask.model.ProfileModel;
import ru.meshgroup.testtask.model.view.ProfileView;
import ru.meshgroup.testtask.service.iface.ProfileService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final ProfileMapper profileMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProfileView.Response.class)
    public Page<ProfileModel> list(@PageableDefault Pageable pageable) {
        return profileService.getPage(pageable)
                .map(profileMapper::mapProfileToRestModel);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProfileView.Response.class)
    public ResponseEntity<ProfileModel> get(@PathVariable Long id) {
        return profileService.getById(id)
                .map(profileMapper::mapProfileToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(ProfileView.Response.class)
    public ResponseEntity<ProfileModel> update(
            @PathVariable Long id,
            @Valid @JsonView(ProfileView.Request.class) @RequestBody ProfileModel profileModel) {
        return Optional.of(profileModel)
                .map(profileMapper::mapRestModelToProfile)
                .flatMap(profile -> profileService.update(id, profile))
                .map(profileMapper::mapProfileToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        profileService.deleteById(id);
    }
}
