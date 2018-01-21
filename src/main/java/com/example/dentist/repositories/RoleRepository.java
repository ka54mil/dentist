package com.example.dentist.repositories;

import com.example.dentist.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByType(Role.Types type);

}
