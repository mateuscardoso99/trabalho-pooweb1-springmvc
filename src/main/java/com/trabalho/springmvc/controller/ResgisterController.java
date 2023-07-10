package com.trabalho.springmvc.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trabalho.springmvc.form.UsuarioForm;
import com.trabalho.springmvc.service.UsuarioService;

@Controller
@RequestMapping(value = "/register")
public class ResgisterController {
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping
    public String registerView(Model model){
        model.addAttribute("user", new UsuarioForm());
        return "register";
    }
    @PostMapping
    public String cadastrar(@ModelAttribute("user") @Valid UsuarioForm usuario, BindingResult result){
        if (result.hasErrors()) {
            return "register";
        }
        this.usuarioService.salvar(usuario);
        return "redirect:/login";
    }
}
