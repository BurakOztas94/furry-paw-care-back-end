package com.oztasburak.furrypawcare.repository;

import com.oztasburak.furrypawcare.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
