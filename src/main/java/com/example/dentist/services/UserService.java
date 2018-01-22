package com.example.dentist.services;

import com.example.dentist.models.Patient;
import com.example.dentist.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void save(User user);

    Page<User> getAllUsers(Pageable pageable);

    User getUser(Long id);


    void saveUser(User user);

    User getById(Long id);

    void delete(Long id);

    boolean exists(Long id);
}
