package com.oztasburak.furrypawcare.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentRequest {
    @Positive
    private Long id;

    @NotBlank
    private LocalDateTime appointmentDate;

    @Positive
    private Long doctorId;

    @Positive
    private Long animalId;
}
