package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.springmvc.form.UsuarioForm;
import com.trabalho.springmvc.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String viewHomeUser(){
        return "principal";
    }

    @GetMapping("/perfil")
    public ModelAndView perfil(ModelAndView modelAndView){
        modelAndView.setViewName("perfil");
        modelAndView.addObject("user", new UsuarioForm());
        return modelAndView;
    }

    @PostMapping("/perfil/update")
    public String update(@ModelAttribute("user") UsuarioForm usuarioForm, HttpServletRequest request){
        this.usuarioService.atualizar(usuarioForm, request);
        return "redirect:/user";
    }

    @PostMapping(value="/apagar-conta")
    public String apagar(HttpServletRequest request) {
        this.usuarioService.deletar(request);
        return "redirect:/logout";
    }
    
}
