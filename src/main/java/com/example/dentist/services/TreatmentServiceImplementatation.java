package com.example.dentist.services;

import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public class TreatmentServiceImplementatation implements TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Override
    public Page<Treatment> getAllTreatments(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }
}
