package com.trabalho.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.trabalho.springmvc.form.LoginForm;

@Controller
public class LoginController {
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("login", new LoginForm());
        return "login";
	}
}
