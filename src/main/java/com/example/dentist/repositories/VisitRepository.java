package com.example.dentist.repositories;

import com.example.dentist.models.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    Visit findById(Long id);

    Page<Visit> findVisitsByPatient_Id(Long patient_id, Pageable pageable);

}
