package com.oztasburak.furrypawcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String diagnosis;

    private double price;

    @OneToOne
    @JoinColumn(columnDefinition = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;
}
