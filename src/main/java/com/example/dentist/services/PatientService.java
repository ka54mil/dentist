package com.example.dentist.services;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Treatment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientService {

   Page<Patient> getAllPatients(Pageable pageable);

    Patient getPatient(Long id);

    void deletePatient(Long id);

    void savePatient(Patient patient);

}
