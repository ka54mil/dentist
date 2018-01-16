package com.example.dentist.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientsController {

    //Controllery - funkcjonalnosc/logika

    //TODO
    @RequestMapping(path = "/patient/register")
    public String register() {
        return "registerForm";
    }
}
