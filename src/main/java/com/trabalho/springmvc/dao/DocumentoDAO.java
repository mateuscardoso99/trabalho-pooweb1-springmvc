package com.trabalho.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;
import com.trabalho.springmvc.repository.DocumentoRepository;

@Repository
public class DocumentoDAO {

	@Autowired
	private DocumentoRepository documentoRepository;

	public List<Documento> findAllByUser(Long idUsuario){
        return documentoRepository.findAllByUser(idUsuario);
    }

    public Documento salvar(Documento documento) {
		return documentoRepository.save(documento);
	}

	public Optional<Documento> findById(Long id, Long userId){
        return documentoRepository.find(id, userId);
    }

	public Optional<Documento> findByFileName(String filename, Long userId){
        return documentoRepository.findByFileName(filename, userId);
    }

	public void deletar(Documento documento){
		documentoRepository.delete(documento);
	}
}
