package com.oztasburak.furrypawcare.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReportResponse {
    private Long id;

    private String title;

    private String diagnosis;

    private double price;

    private OnlyAppointmentResponse appointment;
}
