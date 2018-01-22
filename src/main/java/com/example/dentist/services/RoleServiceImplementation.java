package com.example.dentist.services;


import com.example.dentist.models.Patient;
import com.example.dentist.models.Role;
import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.PatientRepository;
import com.example.dentist.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImplementation implements RoleService {


    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Page<Role> getAllRoles(Pageable pageable) {
        return roleRepository.findAll(pageable);

    }

    @Override
    public Role getRole(Long id) {
        return null;
    }


    @Override
    public void saveRole(Role role) { roleRepository.save(role);
    }
    @Override
    public Role getById(Long id) {
        return roleRepository.findOne(id);
    }

    @Override
    public void delete(Long id){ roleRepository.delete(id); }

    @Override
    public boolean exists(Long id){
        return roleRepository.exists(id);
    }


}
