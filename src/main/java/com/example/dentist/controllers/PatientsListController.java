package com.example.dentist.controllers;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Treatment;
import com.example.dentist.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PatientsListController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value="/patients", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPatientList(Model model, Pageable pageable){
        model.addAttribute("patientListPage", patientService.getAllPatients(pageable));
        return "patients/list";


    }/*

    @RequestMapping(path = "/patients")
    public String index(Model model, Pageable pageable) {
        List<Patient> patients = new ArrayList<>();
        for (int i = pageable.getPageSize() * pageable.getPageNumber() + 1; i <= pageable.getPageSize() * (pageable.getPageNumber() + 1); i++) {
            Patient patient = new Patient(





            );
            patients.add(patient);
        }
        Page page =  new PageImpl<Patient>(patients, pageable, patients.size());

        model.addAttribute("patientsPage", page);

        return "patients/plist";
    }

    @RequestMapping(value={"/patients/add", "/patients/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Patient patient;
        if(id.isPresent()){
            model.addAttribute("action", "edit");
            patient = new Patient(

            );
        } else {
            model.addAttribute("action", "add");
            patient = new Patient();
        }

        model.addAttribute("patient",patient);

        return "patients/pform";
    }

    @RequestMapping(value="/patientForm.html", method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("patient") Patient patient, BindingResult errors){

        if(errors.hasErrors()){
            return "patients/pform";
        }

        return "redirect:/patients";
    }

    @RequestMapping(value="/patients/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/patients";
    }*/

}
