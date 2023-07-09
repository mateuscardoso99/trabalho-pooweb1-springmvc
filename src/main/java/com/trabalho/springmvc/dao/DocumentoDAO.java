package com.trabalho.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;
import com.trabalho.springmvc.repository.DocumentoRepository;

@Repository
public class DocumentoDAO {

	@Autowired DocumentoRepository documentoRepository;

    public List<Documento> findAll(Long idUsuario){
        return documentoRepository.findAll();
    }

    public Documento salvar(Documento documento) {
		return documentoRepository.save(documento);
	}

	public Optional<Documento> findById(Long id){
        return documentoRepository.findById(id);
    }

	public Documento atualizar(Documento documento) {
		return this.salvar(documento);
	}

	public void deletar(Documento documento){
		documentoRepository.delete(documento);
	}
}
