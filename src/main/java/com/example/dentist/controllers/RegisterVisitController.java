package com.example.dentist.controllers;

import com.example.dentist.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Pageable;

@Controller
public class RegisterVisitController {

    @Autowired
    private VisitService visitService;

    @RequestMapping(path = "/registerVisit")
    public String index(Model model, Pageable pageable){
       // model.addAttribute("registerVisit", visitService.getAllVisits(pageable)); //hmm

        return "registerVisit/list";
    }
}
