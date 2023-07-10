package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trabalho.springmvc.form.DocumentoForm;
import com.trabalho.springmvc.service.DocumentoService;

@Controller
@RequestMapping(value = "/user/docs")
public class DocumentoController {
    @Autowired
    private DocumentoService documentoService;

    @GetMapping("/show")
    public String show(Model model, HttpServletRequest request){
        model.addAttribute("documento", new DocumentoForm());
        model.addAttribute("documentos", documentoService.findAllByUser(request));
        return "documento/view";
    }

    @GetMapping("/create")
    public String viewCreate(Model model){
        model.addAttribute("documento", new DocumentoForm());
        return "documento/cadastrar";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DocumentoForm documentoForm, HttpServletRequest request){
        this.documentoService.salvar(documentoForm,request);
        return "redirect:show";
    }

    @GetMapping("/apagar/{id}")
    public String update(@PathVariable Long id){
        this.documentoService.deletar(id);
        return "redirect:show";
    }
}
