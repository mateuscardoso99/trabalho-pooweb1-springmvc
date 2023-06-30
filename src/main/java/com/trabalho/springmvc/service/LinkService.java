package com.trabalho.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.LinkDAO;
import com.trabalho.springmvc.model.Link;

@Service
public class LinkService {
    @Autowired
    private LinkDAO linkDAO;

	public LinkService(){
        this.linkDAO = new LinkDAO();
    }

	@Transactional
	public void salvar(Link link) {
		this.linkDAO.salvar(link);
	}

	@Transactional
	public void atualizar(Link link) {
		this.linkDAO.atualizar(link);
	}

    @Transactional
    public Optional<Link> findById(Long id) {
		return this.linkDAO.findById(id);
	}

	@Transactional
    public List<Link> findAll() {
		return this.linkDAO.findAll();
	}

	@Transactional
	public void deletar(Link link) {
		this.linkDAO.deletar(link);
	}
}
