package com.example.dentist.controllers;

import com.example.dentist.models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.*;

@Controller
public class VisitsController {

    @RequestMapping(path = "/visits/patient")
    public String patientList(Model model, Pageable pageable, Long patient_id) {
        Patient patient = new Patient();
        patient.setId(patient_id);
        patient.setFirst_name("Pacjent");
        patient.setLast_name("Testowy");

        Patient dentistInfo = new Patient();
        dentistInfo.setId(++patient_id);
        dentistInfo.setFirst_name("Dentysta");
        dentistInfo.setLast_name("Nowak");

        User dentist = new User();
        dentist.setId(2L);
        dentist.setEnabled(true);
        dentist.setPatient(dentistInfo);
        Role role = new Role(Role.Types.ROLE_USER);
        dentist.setRoles(new HashSet<>(Arrays.asList(role)));
        List<ScheduledTreatment> scheduledTreatments = new ArrayList<>();
        List<Visit> visits = new ArrayList<>();

        Visit visit = new Visit();
        visit.setId(1L);
        visit.setPatient(patient);
        visit.setDentist(dentist);
        visit.setCreatedDate(new Date());
        visit.setModifiedDate(visit.getCreatedDate());
        visit.setVisitDate(new Date(118,12,30, 16,30));

        for(long id=1;id<3;id++){
            Treatment treatment = new Treatment(id,
                    new BigDecimal(id+"."+id).setScale(2,BigDecimal.ROUND_FLOOR),
                    "Zabieg "+id,
                    true
            );
            ScheduledTreatment scheduledTreatment = new ScheduledTreatment();
            scheduledTreatment.setId(id);
            scheduledTreatment.setPaid(new BigDecimal("0").setScale(2,BigDecimal.ROUND_FLOOR));
            scheduledTreatment.setVisit(visit);
            scheduledTreatment.setTreatment(treatment);
            scheduledTreatments.add(scheduledTreatment);
        }
        visit.setScheduledTreatments(new HashSet<>(scheduledTreatments));
        visits.add(visit);
        visit = new Visit();
        visit.setId(2L);
        visit.setPatient(patient);
        visit.setDentist(dentist);
        visit.setCreatedDate(new Date());
        visit.setModifiedDate(visit.getCreatedDate());
        visit.setVisitDate(new Date(118,10,20, 14,30));

        for(long id=4;id<8;id++){
            Treatment treatment = new Treatment(id,
                    new BigDecimal(id+"."+id).setScale(2,BigDecimal.ROUND_FLOOR),
                    "Zabieg "+id,
                    true
            );
            ScheduledTreatment scheduledTreatment = new ScheduledTreatment();
            scheduledTreatment.setId(id);
            if(id%2==0){
                scheduledTreatment.setPaid(new BigDecimal(id+"."+id).setScale(2,BigDecimal.ROUND_FLOOR));
            } else {
                scheduledTreatment.setPaid(new BigDecimal(0).setScale(2,BigDecimal.ROUND_FLOOR));
            }
            scheduledTreatment.setVisit(visit);
            scheduledTreatment.setTreatment(treatment);
            scheduledTreatments.add(scheduledTreatment);
        }
        visit.setScheduledTreatments(new HashSet<>(scheduledTreatments));
        visits.add(visit);
        Page page =  new PageImpl<>(visits, pageable, visits.size());

        model.addAttribute("visitsPage", page);
        model.addAttribute("patient", patient);

        return "visits/patient_list";
    }
}
