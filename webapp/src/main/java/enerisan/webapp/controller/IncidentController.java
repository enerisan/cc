//package enerisan.webapp.controller;
//
//import enerisan.webapp.dto.IncidentForm;
//import enerisan.webapp.model.User;
//import enerisan.webapp.service.SessionService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
////import org.springframework.web.servlet.ModelAndView;
////
////@Controller
////public class IncidentController {
////
////    @Autowired
////    IncidentService incidentService;
////
////    @Autowired
////    SessionService sessionService;
////
////    @PostMapping("/patient")
////    public ModelAndView processPatient(
////            @ModelAttribute("patientForm") @Valid IncidentForm form,
////            BindingResult result,
////            Model model) {
////
////        if (result.hasErrors()) {
////            return new ModelAndView("addPatient", "patientForm", form);
////        }
////
////        User user = sessionService.sessionUser();
////        patientService.addPatient(form);
////        model.addAttribute("user", user);
////        return new ModelAndView("patientList");
////    }
//
//
//}
