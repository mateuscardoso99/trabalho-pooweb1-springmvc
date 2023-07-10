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

    //BindingResult: objeto do Spring que contém o resultado da validação e vinculação e contém erros que podem ter ocorrido
    //Quando o Spring vê @Valid, ele tenta encontrar o validador para o objeto que está sendo validado. O Spring seleciona automaticamente as anotações de validação se você tiver "orientado por anotação" ativado. O Spring então chama o validador e coloca todos os erros no BindingResulte adiciona o BindingResult ao modelo de exibição.
    @PostMapping
    public String cadastrar(@ModelAttribute("user") @Valid UsuarioForm usuario, BindingResult result){
        if (result.hasErrors()) {
            return "register";
        }
        this.usuarioService.salvar(usuario);
        return "redirect:/login";
    }
}
