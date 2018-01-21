package com.example.dentist.config;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Role;
import com.example.dentist.models.User;
import com.example.dentist.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
public class RepositoriesInitializer {

    @Autowired
    private TreatmentRepository treatmentRepository;
    @Autowired
    private ScheduledTreatmentRepository scheduledTreatmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    InitializingBean init() {

        return () -> {

            if(roleRepository.findAll().isEmpty() == true){
                try {
                    Role roleUser = roleRepository.save(new Role(Role.Types.ROLE_USER));
                    Role roleAdmin = roleRepository.save(new Role(Role.Types.ROLE_ADMIN));
                    Patient userPatient = patientRepository.save(new Patient(null, "First", "User"));
                    Patient adminPatient = patientRepository.save(new Patient(null, "Second", "Admin"));

                    User user = new User(
                            null,
                            new HashSet<>(Arrays.asList(roleUser)),
                            userPatient,
                            "user",
                            passwordEncoder.encode("user"),
                            null,
                            true
                    );
                    user.setPasswordConfirm(null);
                    User admin = new User(
                            null,
                            new HashSet<>(Arrays.asList(roleAdmin)),
                            adminPatient,
                            "admin",
                            passwordEncoder.encode("admin"),
                            null,
                            true
                    );
                    admin.setPasswordConfirm(null);

                    userRepository.save(user);
                    userRepository.save(admin);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        };
    }

}
