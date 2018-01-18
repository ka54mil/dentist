package com.example.dentist.controllers;

import com.example.dentist.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TreatmentsController {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @RequestMapping(path = "/treatments/*")
    public String index(Pageable pageable) {
        Page page = treatmentRepository.findAll(pageable);
        for(int i=0;i<20;i++){

        }
        return "treatments/list";
    }
}
