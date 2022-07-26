package ru.meshgroup.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.testtask.domain.Profile;
import ru.meshgroup.testtask.service.iface.ProfileService;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Profile> list(@PageableDefault Pageable pageable) {
        return profileService.getPage(pageable);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Profile get(@PathVariable Long id) {
        return profileService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Profile add(@RequestBody Profile profile) {
        return profileService.create(profile);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Profile update(@PathVariable Long id, @RequestBody Profile profile) {
        return profileService.update(id, profile);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        profileService.deleteById(id);
    }
}
