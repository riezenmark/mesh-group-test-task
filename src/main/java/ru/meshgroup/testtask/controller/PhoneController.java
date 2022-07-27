package ru.meshgroup.testtask.controller;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.testtask.mapper.PhoneMapper;
import ru.meshgroup.testtask.model.PhoneModel;
import ru.meshgroup.testtask.model.view.PhoneView;
import ru.meshgroup.testtask.service.PhoneService;
import ru.meshgroup.testtask.validation.annotation.AllowedSortProperties;

import javax.validation.Valid;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/api/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;
    private final PhoneMapper phoneMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<PhoneModel> list(@PageableDefault @AllowedSortProperties({"id", "value"}) Pageable pageable) {
        return phoneService.getPage(pageable)
                .map(phoneMapper::mapPhoneToRestModel);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(PhoneView.Response.class)
    public ResponseEntity<PhoneModel> get(@PathVariable Long id) {
        return phoneService.getById(id)
                .map(phoneMapper::mapPhoneToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(PhoneView.Response.class)
    public ResponseEntity<PhoneModel> add(@Valid @JsonView(PhoneView.Request.class) @RequestBody PhoneModel phoneModel) {
        return Optional.of(phoneModel)
                .map(phoneMapper::mapRestModelToPhone)
                .flatMap(phoneService::create)
                .map(phoneMapper::mapPhoneToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PhoneModel> update(
            @PathVariable Long id,
            @Valid @JsonView(PhoneView.Request.class) @RequestBody PhoneModel phoneModel) {
        return Optional.of(phoneModel)
                .map(phoneMapper::mapRestModelToPhone)
                .flatMap(phone -> phoneService.update(id, phone))
                .map(phoneMapper::mapPhoneToRestModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        phoneService.deleteById(id);
    }
}
