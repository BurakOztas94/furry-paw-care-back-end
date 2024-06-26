package com.oztasburak.furrypawcare.controller;

import com.oztasburak.furrypawcare.dto.request.AvailableDateRequest;
import com.oztasburak.furrypawcare.service.AvailableDateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.oztasburak.furrypawcare.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "availableDates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final AvailableDateService availableDateService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (availableDateService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (availableDateService.getResponseById (id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AvailableDateRequest availableDateRequest) {
        return new ResponseEntity<> (availableDateService.create (availableDateRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AvailableDateRequest availableDateRequest
    ) {
        return new ResponseEntity<> (availableDateService.update (id, availableDateRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        availableDateService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
