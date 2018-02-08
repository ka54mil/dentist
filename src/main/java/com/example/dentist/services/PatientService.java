package com.example.dentist.services;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PatientService {

   Page<Patient> getAllPatients(Pageable pageable);

 List<Patient> getAllPatients2();

    Patient getPatient(Long id);


    void savePatient(Patient patient);

    Patient getById(Long id);

    void delete(Long id);

 boolean exists(Long id);



}
