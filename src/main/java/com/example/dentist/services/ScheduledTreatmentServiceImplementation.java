package com.example.dentist.services;

import com.example.dentist.models.ScheduledTreatment;
import com.example.dentist.repositories.ScheduledTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledTreatmentServiceImplementation implements ScheduledTreatmentService {

    @Autowired
    private ScheduledTreatmentRepository scheduledTreatmentRepository;

    @Override
    public ScheduledTreatment getById(Long id) {
        return scheduledTreatmentRepository.findOne(id);
    }

    @Override
    public ScheduledTreatment save(ScheduledTreatment scheduledTreatment) {
        return scheduledTreatmentRepository.save(scheduledTreatment);
    }
}
