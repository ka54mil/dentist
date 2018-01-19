package com.example.dentist.controllers;

import com.example.dentist.models.ScheduledTreatment;
import com.example.dentist.models.Treatment;
import com.example.dentist.models.Visit;
import com.example.dentist.repositories.ScheduledTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Controller
public class ScheduledTreatmentController {

    @RequestMapping(path = "/scheduled_treatments/visit")
    public String visitDetails(Model model, Long visit_id) {
        List<ScheduledTreatment> scheduledTreatments = new ArrayList<>();
        Visit visit = new Visit();
        visit.setId(visit_id);
        visit.setVisitDate(new Date(118,10,20, 14,30));
        for(int i=1;i<=6;i++){
            Treatment treatment = new Treatment(
                    (long)i,
                    new BigDecimal(i+"."+i).setScale(2,BigDecimal.ROUND_FLOOR),
                    "Zabieg "+i,
                    i%4!=0
            );
            ScheduledTreatment scheduledTreatment= new ScheduledTreatment();
            scheduledTreatment.setTreatment(treatment);
            scheduledTreatment.setId((long)i);
            scheduledTreatment.setStatus("Pending");
            scheduledTreatment.setVisit(visit);
            if(i%2==0){
                scheduledTreatment.setPaid(new BigDecimal(i+"."+i).setScale(2,BigDecimal.ROUND_FLOOR));
            } else {
                scheduledTreatment.setPaid(new BigDecimal(0).setScale(2,BigDecimal.ROUND_FLOOR));
            }
            scheduledTreatments.add(scheduledTreatment);
        }

        visit.setScheduledTreatments(new HashSet<>(scheduledTreatments));
        model.addAttribute("visit", visit);

        return "scheduledTreatments/visit";
    }

    @RequestMapping(path = "/scheduled_treatments/pay")
    public String payForScheduledTreatment(Model model, Long scheduledTreatment_id) {

        return "redirect:/scheduled_treatments/visit?visit_id="+1;
    }

    @RequestMapping(path = "/scheduled_treatments/visit/pay")
    public String payForVisit(Model model, Long visit_id) {

        return "redirect:/scheduled_treatments/visit?visit_id="+visit_id;
    }
}
