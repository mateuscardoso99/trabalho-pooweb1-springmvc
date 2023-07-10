package com.trabalho.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Link;
import com.trabalho.springmvc.repository.LinkRepository;

@Repository
public class LinkDAO {
	@Autowired
	private LinkRepository linkRepository;


    public List<Link> findAllByUser(Long idUsuario){
        return linkRepository.findAllByUser(idUsuario);
    }

    public void salvar(Link l) {
		linkRepository.save(l);
	}

	public Optional<Link> findById(Long id){
		return linkRepository.findById(id);
    }

	public void atualizar(Link link) {
		linkRepository.save(link);
	}

	public void deletar(Link link){
		linkRepository.delete(link);
	}
}
