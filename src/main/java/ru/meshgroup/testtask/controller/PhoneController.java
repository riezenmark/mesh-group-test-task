package ru.meshgroup.testtask.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.meshgroup.testtask.domain.Phone;
import ru.meshgroup.testtask.service.iface.PhoneService;

@RestController
@RequestMapping("/api/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<Phone> list(@PageableDefault Pageable pageable) {
        return phoneService.getPage(pageable);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Phone get(@PathVariable Long id) {
        return phoneService.getById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Phone add(@RequestBody Phone phone) {
        return phoneService.create(phone);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Phone update(@PathVariable Long id, @RequestBody Phone phone) {
        return phoneService.update(id, phone);
    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable Long id) {
        phoneService.deleteById(id);
    }
}
