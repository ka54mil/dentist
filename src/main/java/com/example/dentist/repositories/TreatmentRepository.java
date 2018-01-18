package com.example.dentist.repositories;

import com.example.dentist.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
