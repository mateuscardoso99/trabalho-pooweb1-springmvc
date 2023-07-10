package com.trabalho.springmvc.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.LinkDAO;
import com.trabalho.springmvc.form.LinkForm;
import com.trabalho.springmvc.model.Link;
import com.trabalho.springmvc.model.Usuario;

@Service
public class LinkService {
    @Autowired
    private LinkDAO linkDAO;

	public LinkService(){
        this.linkDAO = new LinkDAO();
    }

	@Transactional
	public void salvar(LinkForm linkForm, HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Link link = new Link();
		link.setUrl(linkForm.getUrl());
		link.setDescricao(linkForm.getDescricao());
		link.setUsuario(usuario);
		this.linkDAO.salvar(link);
	}

	@Transactional
	public void atualizar(LinkForm linkForm, HttpServletRequest request) {
		Link link = this.findById(linkForm.getId()).orElseThrow();
		link.setUrl(linkForm.getUrl());
		link.setDescricao(linkForm.getDescricao());
		this.linkDAO.atualizar(link);
	}

    public Optional<Link> findById(Long id) {
		return this.linkDAO.findById(id);
	}

    public List<Link> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.linkDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(String id) {
		Link link = this.findById(Long.valueOf(id)).orElseThrow();
		this.linkDAO.deletar(link);
	}
}
