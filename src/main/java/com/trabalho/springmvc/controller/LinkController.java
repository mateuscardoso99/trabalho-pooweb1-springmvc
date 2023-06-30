package com.trabalho.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.springmvc.model.Link;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.service.LinkService;

@Controller
@RequestMapping(value = "/user/link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView){
        modelAndView.addObject("link", new Link());
        modelAndView.addObject("links", linkService.findAll());
        modelAndView.setViewName("link/view");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model){
        model.addAttribute("link", new Link());
        return "link/cadastrar";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@ModelAttribute Link link){
        link.setUsuario(new Usuario(1l,"joao","joao@joao","1234"));
        this.linkService.salvar(link);
        return "redirect:show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute Link link){
        link.setUsuario(new Usuario(1l,"joao","joao@joao","1234"));
        this.linkService.salvar(link);
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String update(@RequestParam("idLink") String id){
        Link link = this.linkService.findById(Long.valueOf(id)).orElseThrow();
        this.linkService.deletar(link);
        return "redirect:show";
    }
}
