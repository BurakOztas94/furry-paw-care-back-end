package com.oztasburak.furrypawcare.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDateRequest {
    @NotBlank
    private LocalDate availableDate;

    @Positive
    private Long doctorId;
}
