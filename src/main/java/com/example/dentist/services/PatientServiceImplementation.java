package com.example.dentist.services;


import com.example.dentist.models.Patient;
import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImplementation implements PatientService {


    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);

    }

    @Override
    public Patient getPatient(Long id) {
        return null;
    }


    @Override
    public void savePatient(Patient patient) {
        patientRepository.save(patient);
    }
    @Override
    public Patient getById(Long id) {
        return patientRepository.findOne(id);
    }

    @Override
    public void delete(Long id){ patientRepository.delete(id); }

    @Override
    public boolean exists(Long id){
        return patientRepository.exists(id);
    }


}
