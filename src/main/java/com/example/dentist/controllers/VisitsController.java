package com.example.dentist.controllers;

import com.example.dentist.models.*;
import com.example.dentist.services.PatientService;
import com.example.dentist.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @RequestMapping(path = "/visits/patient")
    public String patientList(Model model, Pageable pageable, Long patient_id) {

        model.addAttribute("visitsPage", visitService.getVisitsByPatientId(patient_id, pageable));
        model.addAttribute("patient", patientService.getById(patient_id));

        return "visits/patient_list";
    }

}
