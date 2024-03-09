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
public class VaccineRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private LocalDate protectionStartDate;

    @NotBlank
    private LocalDate protectionFinishDate;

    @Positive
    private Long animalId;

    @Positive
    private Long reportId;
}
