package com.example.dentist.repositories;

import com.example.dentist.models.Role;
import com.example.dentist.models.ScheduledTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledTreatmentRepository extends JpaRepository<ScheduledTreatment, Long> {

    ScheduledTreatment findScheduledTreatmentByTreatment_Id(Long treatment_id);
}

