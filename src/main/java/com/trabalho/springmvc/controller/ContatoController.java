package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.service.ContatoService;

@Controller
@RequestMapping(value = "/user/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.addObject("contato", new ContatoForm());
        modelAndView.addObject("contatos", contatoService.findAllByUser(request));
        modelAndView.setViewName("contato/view");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model){
        model.addAttribute("contato", new ContatoForm());
        return "contato/cadastrar";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute ContatoForm contatoForm, HttpServletRequest request){
        this.contatoService.salvar(contatoForm,request);
        return "redirect:show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute ContatoForm contatoForm, HttpServletRequest request){
        this.contatoService.atualizar(contatoForm, request);
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String apagar(HttpServletRequest request, HttpServletResponse response, @RequestParam("idContato") String id){
        this.contatoService.deletar(request, response, id);
        return "redirect:show";
    }

    @RequestMapping(value="/foto/{file}", method=RequestMethod.GET)
    public void viewFoto(HttpServletRequest request, 
                   HttpServletResponse response, 
                   @PathVariable("file") String fileName) {
        this.contatoService.viewFoto(request, response, fileName);
    }
    
}
