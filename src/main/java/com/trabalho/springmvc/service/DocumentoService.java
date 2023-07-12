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

import com.trabalho.springmvc.dao.DocumentoDAO;
import com.trabalho.springmvc.form.DocumentoForm;
import com.trabalho.springmvc.model.Documento;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUtils;

@Service
public class DocumentoService {
    @Autowired
    private DocumentoDAO documentoDAO;

	private static final String PATH = "docs"+File.separator;

	@Transactional(rollbackFor = Exception.class)
	public void salvar(DocumentoForm documentoForm, HttpServletRequest request) {
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Documento documento = new Documento();
			documento.setArquivo(FileUtils.saveFile(documentoForm.getArquivo(), request.getServletContext().getRealPath("")+PATH));
			documento.setUsuario(usuario);
			this.documentoDAO.salvar(documento);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void update(DocumentoForm documentoForm, HttpServletRequest request){
		try{
			this.deletar(request, documentoForm.getId());
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Documento documento = new Documento();
			documento.setArquivo(FileUtils.saveFile(documentoForm.getArquivo(), request.getServletContext().getRealPath("")+PATH));
			documento.setUsuario(usuario);
			this.documentoDAO.salvar(documento);
		}catch(IOException e){
			e.printStackTrace();
		}
	}

    public Optional<Documento> findById(Long id, Long userId) {
		return this.documentoDAO.findById(id, userId);
	}

	public List<Documento> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.documentoDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(HttpServletRequest request, Long id) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Documento doc = this.findById(id, usuario.getId()).orElseThrow();
		FileUtils.apagarArquivo(request, PATH + doc.getArquivo());
		this.documentoDAO.deletar(doc);
	}

	public void viewDoc(HttpServletRequest request, HttpServletResponse response, String fileName){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		this.documentoDAO.findByFileName(fileName, usuario.getId()).orElseThrow();
		FileUtils.viewFile(request, response, PATH, fileName, false);
	}

	public void downloadDoc(HttpServletRequest request, HttpServletResponse response, String fileName){
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		this.documentoDAO.findByFileName(fileName, usuario.getId()).orElseThrow();
		FileUtils.viewFile(request, response, PATH, fileName, true);
	}
}
