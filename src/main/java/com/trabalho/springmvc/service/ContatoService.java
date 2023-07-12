package com.trabalho.springmvc.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.ContatoDAO;
import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUtils;

@Service
public class ContatoService {
    @Autowired
    private ContatoDAO contatoDAO;

	private static final String PATH = "fotos_contato"+File.separator;

	public ContatoService(){
        this.contatoDAO = new ContatoDAO();
    }

	@Transactional(rollbackFor = {Exception.class})
	public void salvar(ContatoForm cform, HttpServletRequest request) {
		try{
			String fileName = null;

			if(!cform.getFoto().isEmpty())
				fileName = FileUtils.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH);

			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Contato contato = new Contato();
			contato.setNome(cform.getNome());
			contato.setTelefone(cform.getTelefone());
			contato.setFoto(fileName);
			contato.setUsuario(usuario);
			this.contatoDAO.salvar(contato);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	@Transactional
	public void atualizar(ContatoForm cform, HttpServletRequest request) {
		try{
			Contato contato = this.findById(cform.getId()).orElseThrow();

			if(!cform.getFoto().isEmpty()){
				FileUtils.apagarArquivo(request, PATH + contato.getFoto());
				String fileName = FileUtils.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH);
				contato.setFoto(fileName);
			}
			
			contato.setNome(cform.getNome());
			contato.setTelefone(cform.getTelefone());
			this.contatoDAO.salvar(contato);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

    public Optional<Contato> findById(Long id) {
		return this.contatoDAO.findById(id);
	}

    public List<Contato> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.contatoDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(HttpServletRequest request, HttpServletResponse response, String id) {
		Contato c = this.findById(Long.valueOf(id)).orElseThrow();

		if(c.getFoto() != null){
			FileUtils.apagarArquivo(request, PATH + c.getFoto());
		}

		this.contatoDAO.deletar(c);
	}

	public void viewFoto(HttpServletRequest request, HttpServletResponse response, String fileName){
		FileUtils.viewFile(request, response, PATH, fileName, false);
	}
}
