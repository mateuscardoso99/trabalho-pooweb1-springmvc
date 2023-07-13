package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trabalho.springmvc.form.AtualizarContatoForm;
import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.form.DeleteContatoForm;
import com.trabalho.springmvc.service.ContatoService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(value = "/user/contato")
public class ContatoController {
    @Autowired
    private ContatoService contatoService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView show(ModelAndView modelAndView, HttpServletRequest request){
        modelAndView.addObject("contato", new AtualizarContatoForm());
        modelAndView.addObject("deleteContato", new DeleteContatoForm());
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
    public String create(@ModelAttribute("contato") ContatoForm contatoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){
        this.contatoService.salvar(contatoForm,request,bindingResult);

        if(bindingResult.hasErrors()){
            return "contato/cadastrar";
        }

        redirectAttributes.addFlashAttribute("success", "contato cadastrado com sucesso");
        return "redirect:show";
    }

    //redirectAttributes.addFlashAttribute: (flashMessage) addFlashAttribute() armazena os atributos em um flashmap (que é mantido internamente nos usuários sessione removido assim que a próxima solicitação redirecionada é atendida)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("contato") AtualizarContatoForm contatoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){
        this.contatoService.atualizar(contatoForm, request, bindingResult);

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getFieldErrors());
            return "redirect:show";
        }
        redirectAttributes.addFlashAttribute("success", "contato atualizado com sucesso");
        return "redirect:show";
    }

    @RequestMapping(value = "/apagar", method = RequestMethod.POST)
    public String apagar(@ModelAttribute("deleteContato") DeleteContatoForm deleteContatoForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
        this.contatoService.deletar(request, response, deleteContatoForm, bindingResult);

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:show";
        }

        redirectAttributes.addFlashAttribute("success", "contato removido com sucesso");
        return "redirect:show";
    }

    @RequestMapping(value="/foto/{file}", method=RequestMethod.GET)
    public void viewFoto(HttpServletRequest request, HttpServletResponse response, @PathVariable("file") String fileName) {
        this.contatoService.viewFoto(request, response, fileName);
    }

    @GetMapping(value="exportar")
    public void exportarContatos(HttpServletRequest request, HttpServletResponse response) {
        this.contatoService.exportarCSV(request, response);
    }
}
