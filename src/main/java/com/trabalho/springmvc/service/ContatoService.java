package com.trabalho.springmvc.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.ContatoDAO;
import com.trabalho.springmvc.form.ContatoForm;
import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUpload;

@Service
public class ContatoService {
    @Autowired
    private ContatoDAO contatoDAO;

	private static final String PATH = File.separator+"fotos_contato"+File.separator;

	public ContatoService(){
        this.contatoDAO = new ContatoDAO();
    }

	@Transactional(rollbackFor = {Exception.class})
	public void salvar(ContatoForm cform, HttpServletRequest request) {
		try{
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Contato contato = new Contato();
			contato.setNome(cform.getNome());
			contato.setTelefone(cform.getTelefone());
			contato.setFoto(FileUpload.saveFile(cform.getFoto(), request.getServletContext().getRealPath("")+PATH));
			contato.setUsuario(usuario);
			this.contatoDAO.salvar(contato);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Transactional
	public void atualizar(ContatoForm cform, HttpServletRequest request) {
		Contato contato = this.findById(cform.getId()).orElseThrow();
		contato.setNome(cform.getNome());
		contato.setTelefone(cform.getTelefone());
		this.contatoDAO.salvar(contato);
	}

    public Optional<Contato> findById(Long id) {
		return this.contatoDAO.findById(id);
	}

    public List<Contato> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.contatoDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(Long id) {
		Contato c = this.findById(id).orElseThrow();
		this.contatoDAO.deletar(c);
	}
}
