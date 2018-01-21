package com.example.dentist.services;

import com.example.dentist.models.ScheduledTreatment;
import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.ScheduledTreatmentRepository;
import com.example.dentist.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;

@Service
public class TreatmentServiceImplementatation implements TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private ScheduledTreatmentRepository scheduledTreatmentRepository;

    @Override
    public Page<Treatment> getAllTreatments(Pageable pageable) {
        return treatmentRepository.findAll(pageable);
    }

    @Override
    public Treatment save(Treatment treatment){
        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment getById(Long id) {
        return treatmentRepository.findOne(id);
    }

    @Override
    public void delete(Long id){ treatmentRepository.delete(id); }

    @Override
    public boolean exists(Long id){
        return treatmentRepository.exists(id);
    }

    @Override
    public boolean isAssignedToAnyScheduledTreatment(Long id) {

        return scheduledTreatmentRepository.findScheduledTreatmentByTreatment_Id(id)!=null;
    }
}
