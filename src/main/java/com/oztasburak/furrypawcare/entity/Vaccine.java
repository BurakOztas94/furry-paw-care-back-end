package com.oztasburak.furrypawcare.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vaccine {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    @ManyToOne
    @JoinColumn(columnDefinition = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(columnDefinition = "report_id", referencedColumnName = "id")
    private Report report;
}
