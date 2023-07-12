package com.trabalho.springmvc.controller;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class HandleExceptions {
    @ExceptionHandler(value = NoSuchElementException.class)
    public String handle(RedirectAttributes redirectAttributes, HttpServletRequest request, NoSuchElementException ex) {
        String urlAnterior = request.getHeader("Referer") != null ? request.getHeader("Referer") : "/";
        redirectAttributes.addFlashAttribute("erro", "valor não encontrado.");
        return "redirect:"+urlAnterior;
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
