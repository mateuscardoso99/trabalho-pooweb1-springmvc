package com.trabalho.springmvc.service;

import java.io.File;
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

    public Optional<Documento> findById(Long id) {
		return this.documentoDAO.findById(id);
	}

	public List<Documento> findAllByUser(HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		return this.documentoDAO.findAllByUser(usuario.getId());
	}

	@Transactional
	public void deletar(HttpServletRequest request, HttpServletResponse response, Long id) {
		Documento doc = this.findById(id).orElseThrow();
		FileUtils.apagarArquivo(request, response, PATH + doc.getArquivo());
		this.documentoDAO.deletar(doc);
	}

	public void viewDoc(HttpServletRequest request, HttpServletResponse response, String fileName){
		FileUtils.viewFile(request, response, PATH, fileName, false);
	}

	public void downloadDoc(HttpServletRequest request, HttpServletResponse response, String fileName){
		FileUtils.viewFile(request, response, PATH, fileName, true);
	}
}
