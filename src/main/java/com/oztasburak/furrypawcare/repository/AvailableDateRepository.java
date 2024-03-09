package com.oztasburak.furrypawcare.repository;

import com.oztasburak.furrypawcare.entity.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableDateRepository extends JpaRepository<AvailableDate, Long> {
}
