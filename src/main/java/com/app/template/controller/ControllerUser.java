package com.app.template.controller;

import com.app.template.entity.ModelUsers;
import com.app.template.service.ServiceUser;
import com.app.template.service.ServiceToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerUser {
    
    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceToken serviceToken;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView formRegistrasi(ModelAndView modelAndView, ModelUsers users){
        modelAndView.addObject("users", users);
        modelAndView.setViewName("register/register");
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, ModelUsers users){
        serviceUser.findByUsername(modelAndView, users);
        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken){
        serviceToken.findbyToken(modelAndView, confirmationToken);
        return modelAndView;
    }
}
