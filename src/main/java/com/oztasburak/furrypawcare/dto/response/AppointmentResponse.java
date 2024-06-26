package com.oztasburak.furrypawcare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentResponse {
    private Long id;

    private LocalDateTime appointmentDate;

    private OnlyDoctorResponse doctor;

    private OnlyAnimalResponse animal;

    private OnlyReportResponse report;
}
