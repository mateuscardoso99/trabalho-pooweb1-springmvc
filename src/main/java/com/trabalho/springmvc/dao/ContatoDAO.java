package com.trabalho.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Contato;
import com.trabalho.springmvc.repository.ContatoRepository;


@Repository
public class ContatoDAO {
	@Autowired
	private ContatoRepository contatoRepository;

    public List<Contato> findAll(){
		return contatoRepository.findAll();
    }

    public void salvar(Contato c) {
		contatoRepository.save(c);
	}

	public Optional<Contato> findById(Long id){
        return contatoRepository.findById(id);
    }

	public void atualizar(Contato c) {
		contatoRepository.save(c);
	}

	public void deletar(Contato c){
		contatoRepository.delete(c);
	}
}
