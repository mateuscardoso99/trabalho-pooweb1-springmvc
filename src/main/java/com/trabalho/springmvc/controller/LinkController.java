package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.springmvc.form.LinkForm;
import com.trabalho.springmvc.service.LinkService;

@Controller
@RequestMapping(value = "/user/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.addObject("link", new LinkForm());
        modelAndView.addObject("links", linkService.findAllByUser(request));
        modelAndView.setViewName("link/view");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model){
        model.addAttribute("link", new LinkForm());
        return "link/cadastrar";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute LinkForm link, HttpServletRequest request){
        this.linkService.salvar(link, request);
        return "redirect:show";
    }   

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute LinkForm link, HttpServletRequest request){
        this.linkService.atualizar(link, request);
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String apagar(@RequestParam("idLink") String id){
        this.linkService.deletar(id);
        return "redirect:show";
    }
}
