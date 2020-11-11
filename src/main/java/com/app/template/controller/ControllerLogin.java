package com.app.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerLogin {
    
    @GetMapping("/login")
    public String login(){
        return "index";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

}