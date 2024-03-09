package com.oztasburak.furrypawcare.repository;

import com.oztasburak.furrypawcare.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
