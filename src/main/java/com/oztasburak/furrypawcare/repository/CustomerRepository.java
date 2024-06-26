package com.oztasburak.furrypawcare.repository;

import com.oztasburak.furrypawcare.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByNameIgnoringCaseContaining (String name);
}
