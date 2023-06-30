package com.trabalho.springmvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.ContatoDAO;
import com.trabalho.springmvc.model.Contato;

@Service
public class ContatoService {
    @Autowired
    private ContatoDAO contatoDAO;

	public ContatoService(){
        this.contatoDAO = new ContatoDAO();
    }

	@Transactional
	public void salvar(Contato c) {
		this.contatoDAO.salvar(c);
	}

	@Transactional
	public void atualizar(Contato c) {
		this.contatoDAO.atualizar(c);
	}

    @Transactional
    public Optional<Contato> findById(Long id) {
		return this.contatoDAO.findById(id);
	}

	@Transactional
    public List<Contato> findAll() {
		return this.contatoDAO.findAll();
	}

	@Transactional
	public void deletar(Contato c) {
		this.contatoDAO.deletar(c);
	}
}
