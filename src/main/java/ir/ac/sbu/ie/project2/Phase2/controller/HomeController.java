package ir.ac.sbu.ie.project2.Phase2.controller;

import ir.ac.sbu.ie.project2.Phase2.model.Case;
import ir.ac.sbu.ie.project2.Phase2.model.User;
import ir.ac.sbu.ie.project2.Phase2.service.CaseService;
import ir.ac.sbu.ie.project2.Phase2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    UserService userService;

    @Autowired
    CaseService caseService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("case", new Case());
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
    public ModelAndView registerCase(@Valid Case userCase, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in the form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else {
            UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userCase.setUser(userService.findUserByEmail(userDetails.getUsername()));
            caseService.saveCase(userCase);
            modelAndView.addObject("successMessage", "Case is successfully reported!");
            userCase = new Case();
        }

        modelAndView.addObject("case", userCase);
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
