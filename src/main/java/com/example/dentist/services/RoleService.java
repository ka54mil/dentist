package com.example.dentist.services;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Role;
import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {

    Page<Role> getAllRoles(Pageable pageable);

    Role getRole(Long id);


    void saveRole(Role role);

    Role getById(Long id);

    void delete(Long id);

    boolean exists(Long id);



}