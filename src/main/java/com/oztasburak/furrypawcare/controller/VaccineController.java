package com.oztasburak.furrypawcare.controller;

import com.oztasburak.furrypawcare.dto.request.VaccineRequest;
import com.oztasburak.furrypawcare.service.VaccineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.oztasburak.furrypawcare.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "vaccines")
@RequiredArgsConstructor
public class VaccineController {
    private final VaccineService vaccineService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (vaccineService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (vaccineService.getResponseById (id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody VaccineRequest vaccineRequest) {
        return new ResponseEntity<> (vaccineService.create (vaccineRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody VaccineRequest vaccineRequest
    ) {
        return new ResponseEntity<> (vaccineService.update (id, vaccineRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        vaccineService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
