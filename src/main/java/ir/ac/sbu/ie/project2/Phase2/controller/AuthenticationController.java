package ir.ac.sbu.ie.project2.Phase2.controller;

import ir.ac.sbu.ie.project2.Phase2.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login"); // resources/template/login.html
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register"); // resources/template/register.html
        return modelAndView;
    }

//    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {""})
//    public ModelAndView registerUser(@RequestBody User user) {
//        System.out.println(user.getEmail());
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home"); // resources/template/home.html
//        return modelAndView;
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home"); // resources/template/home.html
        return modelAndView;
    }
}
