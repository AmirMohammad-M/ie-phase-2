package ir.ac.sbu.ie.project2.Phase2.controller;

import ir.ac.sbu.ie.project2.Phase2.model.User;
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
public class AuthenticationController {
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in the form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "User already exists!");
        }
        // we will save the user if, no binding errors
        else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User is registered successfully!");
            user = new User();
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView();
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findUserByEmail(userDetails.getUsername());
        modelAndView.addObject("user", new User(user.getName(), user.getLastName(), user.getEmail()));
        modelAndView.setViewName("profile"); // resources/template/register.html
        return modelAndView;
    }

    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public ModelAndView modifyUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView modelAndView = new ModelAndView();
        // Check for the validations
        if(bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in the form!");
            modelMap.addAttribute("bindingResult", bindingResult);
        }
        else if(!user.getEmail().equals(userDetails.getUsername()) || !userService.isUserAlreadyPresent(user)){
            modelAndView.addObject("successMessage", "Invalid user!");
        }
        // we will save the user if, no binding errors
        else {
            userService.updateUserByEmail(user.getEmail(), user.getName(), user.getLastName(), user.getPassword());
            modelAndView.addObject("successMessage", "Profiled was modified successfully!");
        }
        modelAndView.addObject("user", user);
        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
