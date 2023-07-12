package com.trabalho.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.trabalho.springmvc.form.DocumentoForm;
import com.trabalho.springmvc.service.DocumentoService;
import com.trabalho.springmvc.utils.FileUtils;

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
    public String create(@ModelAttribute DocumentoForm documentoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, HttpServletRequest request){
        FileUtils.validateDocumento(documentoForm.getArquivo(), bindingResult);
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:show";
        }
        this.documentoService.salvar(documentoForm,request);
        redirectAttributes.addFlashAttribute("success", "arquivo salvo com sucesso");
        return "redirect:show";
    }

    @PostMapping("/update")
    public ModelAndView update(
        @ModelAttribute("documento") DocumentoForm documentoForm, 
        BindingResult bindingResult,
        HttpServletRequest request,
        RedirectAttributes redirectAttributes
    ){
        FileUtils.validateDocumento(documentoForm.getArquivo(), bindingResult);
        
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return new ModelAndView("redirect:show");
        }
        this.documentoService.update(documentoForm, request);
        redirectAttributes.addFlashAttribute("success", "documento atualizado com sucesso");
        return new ModelAndView("redirect:/user/docs/show");
    }

    @GetMapping("/apagar/{id}")
    public ModelAndView apagar(HttpServletRequest request, RedirectAttributes redirectAttributes, @PathVariable("id") Long id){
        this.documentoService.deletar(request, id);
        redirectAttributes.addFlashAttribute("success", "arquivo removido com sucesso");
        return new ModelAndView("redirect:/user/docs/show");
    }

    @RequestMapping(value="/arquivo/{file}", method=RequestMethod.GET)
    public void viewDoc(HttpServletRequest request, HttpServletResponse response, @PathVariable("file") String fileName) {
        this.documentoService.viewDoc(request, response, fileName);
    }

    @RequestMapping(value="/download-arquivo/{file}", method=RequestMethod.GET)
    public void downloadDoc(HttpServletRequest request, HttpServletResponse response, @PathVariable("file") String fileName) {
        this.documentoService.downloadDoc(request, response, fileName);
    }
}
