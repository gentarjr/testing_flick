package com.app.template.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerIndex {
    
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
