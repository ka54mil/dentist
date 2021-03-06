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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class TreatmentsController {

    @Autowired
    private TreatmentService treatmentService;

    @RequestMapping(path = "/treatments")
    public String index(Model model, Pageable pageable) {
        model.addAttribute("treatmentsPage", treatmentService.getAllTreatments(pageable));

        return "treatments/list";
    }

    @RequestMapping(path = "/treatments/details")
    public String details(Model model, Long id) {
        Treatment treatment = treatmentService.getById(id);
        model.addAttribute("treatment", treatment);

        return "treatments/details";
    }

    @RequestMapping(value={"/treatments/add", "/treatments/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Optional<Long> id){
        Treatment treatment;
        if(id.isPresent()){
            Long treatmentId = id.get();
            model.addAttribute("action", "edit");
            treatment = treatmentService.getById(treatmentId);
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
        Long id = treatment.getId();
        if(id != null){
            Treatment oldTreatment = treatmentService.getById(id);

            if(!oldTreatment.getPrice().equals(treatment.getPrice()) && treatmentService.isAssignedToAnyScheduledTreatment(id)){
                oldTreatment.setActive(false);
                treatmentService.save(oldTreatment);
                treatment.setId(null);
            }
        }

        treatmentService.save(treatment);

        return "redirect:/treatments";
    }

    @RequestMapping(value="/treatments/change_active")
    public String changeActive(Long id){

        Treatment treatment = treatmentService.getById(id);
        treatment.setActive(!treatment.isActive());
        treatmentService.save(treatment);

        return "redirect:/treatments";
    }

    @RequestMapping(value="/treatments/delete")
    public String delete(Model model, Long id){

        if(treatmentService.exists(id)){
            if(treatmentService.isAssignedToAnyScheduledTreatment(id)){
                model.addAttribute("treatment",treatmentService.getById(id));
                return "/treatments/can_not_delete";
            }
            treatmentService.delete(id);
        }
        return "redirect:/treatments";
    }
}
