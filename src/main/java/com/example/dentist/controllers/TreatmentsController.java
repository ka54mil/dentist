package com.example.dentist.controllers;

import com.example.dentist.models.Treatment;
import com.example.dentist.repositories.TreatmentRepository;
import com.example.dentist.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
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

@Controller
public class TreatmentsController {

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(path = "/treatments")
    public String index(Model model, Pageable pageable) {
        List<Treatment> treatments = new ArrayList<>();
        for(int i=pageable.getPageSize()*pageable.getPageNumber()+1;i<=pageable.getPageSize()*(pageable.getPageNumber()+1);i++){
            Treatment treatment = new Treatment(
                    (long)i,
                    new BigDecimal(i+"."+i).setScale(2,BigDecimal.ROUND_FLOOR),
                    "Zabieg "+i,
                    i%4!=0
            );
            treatments.add(treatment);
        }

        Page page =  new PageImpl<>(treatments, pageable, treatments.size());

        model.addAttribute("treatmentsPage", page);

        return "treatments/list";
    }

    @RequestMapping(path = "/treatments/details")
    public String details(Model model, Long id) {
        Treatment treatment = new Treatment(id,
                new BigDecimal(id+"."+id).setScale(2,BigDecimal.ROUND_FLOOR),
                "Zabieg "+id,
                id%4!=0
        );
        model.addAttribute("treatment", treatment);

        return "treatments/details";
    }

    @RequestMapping(value={"/treatments/add", "/treatments/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Treatment treatment;
        if(id.isPresent()){
            Long treatmentId = id.get();
            model.addAttribute("action", "edit");
            treatment = new Treatment(treatmentId,
                    new BigDecimal(treatmentId+"."+treatmentId).setScale(2,BigDecimal.ROUND_FLOOR),
                    "Zabieg "+treatmentId,
                    treatmentId%4!=0
            );
        } else {
            model.addAttribute("action", "add");
            treatment = new Treatment();


        }

        model.addAttribute("treatment",treatment);

        return "treatments/form";
    }

    @RequestMapping(value={"/treatments/add", "/treatments/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("treatment") Treatment treatment, BindingResult errors){

//        if(errors.hasErrors()){
//            return "treatments/form";
//        }

        return "redirect:/treatments";
    }

    @RequestMapping(value="/treatments/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/treatments";
    }

    @RequestMapping(value="/treatments/delete")
    public String delete(Model model, Long id){

        return "redirect:/treatments";
    }
}
