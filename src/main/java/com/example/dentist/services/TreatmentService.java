package com.example.dentist.services;

import com.example.dentist.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TreatmentService {
    Page<Treatment> getAllTreatments(Pageable pageable);

    Treatment save(Treatment treatment);

    Treatment getById(Long id);

    void delete(Long id);

    boolean exists(Long id);

}
