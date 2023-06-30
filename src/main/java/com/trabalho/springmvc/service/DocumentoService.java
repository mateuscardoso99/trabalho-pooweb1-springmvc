package com.trabalho.springmvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.dao.DocumentoDAO;
import com.trabalho.springmvc.model.Documento;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoDAO documentoDAO;

	public DocumentoService(){
        this.documentoDAO = new DocumentoDAO();
    }

	@Transactional
	public void salvar(Documento documento) {
		this.documentoDAO.salvar(documento);
	}

	@Transactional
	public void atualizar(Documento documento) {
		this.documentoDAO.atualizar(documento);
	}

    @Transactional
    public Optional<Documento> findById(Long id) {
		return this.documentoDAO.findById(id);
	}

	@Transactional
	public void deletar(Documento documento) {
		this.documentoDAO.deletar(documento);
	}
}
