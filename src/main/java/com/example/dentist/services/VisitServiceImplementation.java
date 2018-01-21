package com.example.dentist.services;

import com.example.dentist.models.Patient;
import com.example.dentist.models.User;
import com.example.dentist.models.Visit;
import com.example.dentist.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VisitServiceImplementation implements VisitService {

    @Autowired
    private VisitRepository visitRepository;


    @Override
    public Page<Visit> getAllVisits(Pageable pageable) {
        return visitRepository.findAll(pageable);
    }

    @Override
    public Page<Visit> getVisitsByPatientId(Long patient_id,Pageable pageable) {
        return visitRepository.findVisitsByPatient_Id(patient_id, pageable);
    }

    @Override
    public Visit getVisit(Long id) {
        return visitRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        visitRepository.delete(id);
    }

    @Override
    public void add(Date visiteDate, Patient patient, User dentist) {

    }

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }
}
