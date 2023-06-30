package com.trabalho.springmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.service.ContatoService;

@Controller
@RequestMapping(value = "/user/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView){
        modelAndView.addObject("contatos", contatoService.findAll());
        modelAndView.setViewName("contato/view");
        return modelAndView;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model){
        model.addAttribute("contato", new Contato());
        return "contato/cadastrar";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam("foto") MultipartFile foto, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone){
        Contato contato = new Contato(nome, telefone, null, new Usuario(1l,"joao","joao@joao","1234"));
        this.contatoService.salvar(contato);
        return "redirect:show";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("idContato") String id, @RequestParam("nome") String nome, @RequestParam("telefone") String telefone){
        this.contatoService.salvar(new Contato(Long.valueOf(id), nome, telefone, null,new Usuario(1l,"joao","joao@joao","1234")));
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String update(@RequestParam("idContato") String id){
        Contato c = this.contatoService.findById(Long.valueOf(id)).orElseThrow();
        this.contatoService.deletar(c);
        return "redirect:show";
    }
}
