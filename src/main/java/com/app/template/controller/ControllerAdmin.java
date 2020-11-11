package com.app.template.controller;

import java.util.List;

import com.app.template.entity.ModelUsers;
import com.app.template.service.ServiceAdmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerAdmin{

    @Autowired
    private ServiceAdmin service;

    @GetMapping("/admin")
    public String listAdmin(Model model){
        Page<ModelUsers> page = service.listAllUser();
        List<ModelUsers> list = page.getContent();
        model.addAttribute("list_user", list);
        return "admin/list-user";
    }

    @GetMapping("/admin/accept/{id}")
    public String accept(Model model, @PathVariable("id") Long idUsers){
        ModelUsers users = service.getUsers(idUsers);
        model.addAttribute("users", users);
        return "admin/edit-user";
    }

    @PostMapping("/admin/accept")
    public String accept(Model model, @ModelAttribute("users") ModelUsers users){
        service.save(users);
        return "redirect:/admin";
    }
}