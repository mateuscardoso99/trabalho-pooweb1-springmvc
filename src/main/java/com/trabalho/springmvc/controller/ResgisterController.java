package com.trabalho.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.service.UsuarioService;

@Controller
@RequestMapping(value = "/register")
public class ResgisterController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping
    public String registerView(Model model){
        model.addAttribute("user", new Usuario());
        return "register";
    }
    @PostMapping
    public String cadastrar(@ModelAttribute("user") Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        this.usuarioService.salvar(usuario);
        return "login";
    }
}
