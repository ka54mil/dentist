package com.example.dentist.controllers;

import com.example.dentist.models.Patient;
import com.example.dentist.models.Treatment;
import com.example.dentist.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names={})

public class PatientsListController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value="/patients", method = {RequestMethod.GET, RequestMethod.POST})
    public String showPatientList(Model model, Pageable pageable){
        model.addAttribute("patientListPage", patientService.getAllPatients(pageable));
        return "patients/plist";


    }
    @Secured("IS_AUTHENTICATED_FULLY")
    @RequestMapping(value="/patients/plist", params = "id", method = RequestMethod.GET)
    public String showPatientDetails(Model model, Long id){
        //log.info("Pokazywanie szczegółów");
        model.addAttribute("patient", patientService.getPatient(id));
        return "PatientDetails";
    }



   /* @RequestMapping(path = "/patients")
    public String index(Model model, Pageable pageable) {
        List<Patient> patients = new ArrayList<>();


        Page page =  new PageImpl<Patient>(patients, pageable, patients.size());

        model.addAttribute("patientsPage", page);

        return "patients/plist";
    }*/

    @RequestMapping(value={"/patients/add", "/patients/edit"}, method= RequestMethod.GET)
    public String showForm(Model model, Patient patient){
       // Patient patient;


            model.addAttribute("action", "edit");
            patient = new Patient();

            model.addAttribute("action", "add");
            patient = new Patient(

            );


        model.addAttribute("patient",patient);

        return "patients/pform";
    }

    @RequestMapping(value={"/patients/add", "/patients/edit"}, method= RequestMethod.POST)
    public String processForm(@Valid @ModelAttribute("patient") Patient patient, BindingResult errors){

//        if(errors.hasErrors()){
//            return "patients/form";
//        }

        return "redirect:/patients";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping(path="/patient/delete", params={"did"})
    public String deletePatient(long did, HttpServletRequest request){
        patientService.deletePatient(did);
        String queryString = prepareQueryString(request.getQueryString());
        return String.format("redirect:/patients", queryString);//robimy przekierowanie, ale zachowując parametry pageingu
    }
    private String prepareQueryString(String queryString) {//np., did=20&page=2&size=20
        return queryString.substring(queryString.indexOf("&")+1);//obcinamy parametr did, bo inaczej po przekierowaniu znowu będzie wywołana metoda deleteVihicle
    }

    @RequestMapping(value="/patients/deactivate")
    public String deactivate(Model model, Long id){

        return "redirect:/patients";
    }

}
