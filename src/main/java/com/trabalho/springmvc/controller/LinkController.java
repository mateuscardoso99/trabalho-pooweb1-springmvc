package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String create(@ModelAttribute("link") @Valid LinkForm link, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "link/cadastrar";
        }
        this.linkService.salvar(link, request);
        redirectAttributes.addFlashAttribute("success", "link salvo com sucesso");
        return "redirect:show";
    }   

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("link") @Valid LinkForm link, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:show";
        }
        this.linkService.atualizar(link, request);
        redirectAttributes.addFlashAttribute("success", "link atualizado com sucesso");
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String apagar(@RequestParam("idLink") String id, RedirectAttributes redirectAttributes){
        this.linkService.deletar(id);
        redirectAttributes.addFlashAttribute("success", "link removido com sucesso");
        return "redirect:show";
    }
}
