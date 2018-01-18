package com.example.dentist.controllers;

import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TreatmentsController {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @RequestMapping(path = "/treatments")
    public String index(Model model, Pageable pageable) {
        List<Treatment> treatments = new ArrayList<>();
        for(int i=pageable.getPageSize()*pageable.getPageNumber()+1;i<=pageable.getPageSize()*(pageable.getPageNumber()+1);i++){
            Treatment treatment = new Treatment();
            treatment.setId((long) i);
            treatment.setName("Zabieg "+i);
            treatment.setPrice(new BigDecimal(i+"."+i));
            treatment.setActive(i%4!=0);
            treatments.add(treatment);
        }

        Page page =  new PageImpl<Treatment>(treatments, pageable, treatments.size());

        model.addAttribute("treatmentsPage", page);

        return "treatments/list";
    }
}
