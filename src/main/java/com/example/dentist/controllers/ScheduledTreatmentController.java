package com.example.dentist.controllers;

import com.example.dentist.models.ScheduledTreatment;
import com.example.dentist.models.Treatment;
import com.example.dentist.models.Visit;
import com.example.dentist.repositories.ScheduledTreatmentRepository;
import com.example.dentist.services.ScheduledTreatmentService;
import com.example.dentist.services.VisitService;
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

    @Autowired
    private VisitService visitService;

    @Autowired
    private ScheduledTreatmentService scheduledTreatmentService;

    @RequestMapping(path = "/scheduled_treatments/visit")
    public String visitDetails(Model model, Long visit_id) {
        Visit visit = visitService.getVisit(visit_id);
        BigDecimal paid = visit.getPaid();
        BigDecimal toPay = visit.getPrice();

        model.addAttribute("visit", visit);
        model.addAttribute("paid", paid);
        model.addAttribute("toPay", toPay);
        model.addAttribute("paidFullPrice", paid.compareTo(toPay) > -1);

        return "scheduledTreatments/visit";
    }

    @RequestMapping(path = "/scheduled_treatments/pay")
    public String payForScheduledTreatment(Model model, Long id) {

        ScheduledTreatment scheduledTreatment = scheduledTreatmentService.getById(id);
        scheduledTreatment = payForScheduledTreatment(scheduledTreatment);

        return "redirect:/scheduled_treatments/visit?visit_id="+scheduledTreatment.getVisit().getId();
    }

    @RequestMapping(path = "/scheduled_treatments/visit/pay")
    public String payForVisit(Model model, Long visit_id) {
        Visit visit = visitService.getVisit(visit_id);

        for (ScheduledTreatment scheduledTreatment:visit.getScheduledTreatments()) {
            payForScheduledTreatment(scheduledTreatment);
        }

        return "redirect:/scheduled_treatments/visit?visit_id="+visit_id;
    }

    public ScheduledTreatment payForScheduledTreatment(ScheduledTreatment scheduledTreatment) {

        scheduledTreatment.setPaid(scheduledTreatment.getTreatment().getPrice());
        scheduledTreatment.setStatus("Pending.to.complete");
        return scheduledTreatmentService.save(scheduledTreatment);
    }
}
