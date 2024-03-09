package com.oztasburak.furrypawcare.controller;

import com.oztasburak.furrypawcare.dto.request.AppointmentRequest;
import com.oztasburak.furrypawcare.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.oztasburak.furrypawcare.config.BaseURL.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "appointments")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<> (appointmentService.getAllResponses (), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return new ResponseEntity<> (appointmentService.getResponseById (id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody AppointmentRequest appointmentRequest) {
        return new ResponseEntity<> (appointmentService.create (appointmentRequest), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody AppointmentRequest appointmentRequest
    ) {
        return new ResponseEntity<> (appointmentService.update (id, appointmentRequest), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteById (id);
        return new ResponseEntity<> (HttpStatus.NO_CONTENT);
    }
}