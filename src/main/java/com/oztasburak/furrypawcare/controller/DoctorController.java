package com.oztasburak.furrypawcare.controller;

import com.oztasburak.furrypawcare.dto.request.DoctorRequest;
import com.oztasburak.furrypawcare.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.oztasburak.furrypawcare.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "doctors")
@RequiredArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (doctorService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (doctorService.getResponseById (id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody DoctorRequest doctorRequest) {
        return new ResponseEntity<> (doctorService.create (doctorRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody DoctorRequest doctorRequest
    ) {
        return new ResponseEntity<> (doctorService.update (id, doctorRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        doctorService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}
