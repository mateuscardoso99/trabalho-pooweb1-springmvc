package com.trabalho.springmvc.controller;

import java.util.NoSuchElementException;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandleExceptions {
    @ExceptionHandler(value = NoSuchElementException.class)
    public String handle(Model model, NoSuchElementException ex) {
        model.addAttribute("erro", "valor não encontrado.");
        return "error";
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public String handle(Model model, NumberFormatException ex){
        model.addAttribute("erro", "valor inválido");
        return "error";
    }

    @ExceptionHandler(value = Exception.class)
    public String handle(Model model, Exception ex) {
        model.addAttribute("erro", ex.getMessage());
        return "error";
    }
}
