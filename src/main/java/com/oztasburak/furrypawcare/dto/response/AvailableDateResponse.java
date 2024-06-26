package com.oztasburak.furrypawcare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvailableDateResponse {
    private Long id;

    private LocalDate availableDate;

    private OnlyDoctorResponse doctor;
}
