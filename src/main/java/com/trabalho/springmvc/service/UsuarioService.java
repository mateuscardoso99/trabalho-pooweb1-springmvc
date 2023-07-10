package com.trabalho.springmvc.service;

import java.io.File;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.trabalho.springmvc.dao.UsuarioDAO;
import com.trabalho.springmvc.form.UsuarioForm;
import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.utils.FileUtils;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

	@Autowired
    private PasswordEncoder passwordEncoder;

	private static final String PATH_FOTOS = "fotos_contato"+File.separator;
	private static final String PATH_DOCS = "docs"+File.separator;

	@Transactional
	public Usuario salvar(UsuarioForm usuarioForm) {
		Usuario u = new Usuario();
		u.setNome(usuarioForm.getNome());
		u.setEmail(usuarioForm.getEmail());
		u.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));
		return this.usuarioDAO.salvar(u);
	}

	@Transactional
	public void atualizar(UsuarioForm usuarioForm, HttpServletRequest request) {
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		usuario.setNome(usuarioForm.getNome());
		usuario.setEmail(usuarioForm.getEmail());

		if(usuarioForm.getSenha() != ""){
			usuario.setSenha(passwordEncoder.encode(usuarioForm.getSenha()));
		}
		this.usuarioDAO.atualizar(usuario);
	}

    public Optional<Usuario> findById(Long id) {
		return this.usuarioDAO.findById(id);
	}

	@Transactional
	public void deletar(HttpServletRequest request) {
		Usuario usuarioSession = (Usuario) request.getSession().getAttribute("usuario");
		Usuario u = this.usuarioDAO.findByEmail(usuarioSession.getEmail()).orElseThrow();
		u.getContatos().forEach(c -> FileUtils.apagarArquivo(request,  PATH_FOTOS + c.getFoto()));
		u.getDocumentos().forEach(doc -> FileUtils.apagarArquivo(request, PATH_DOCS + doc.getArquivo()));
		this.usuarioDAO.deletar(u);
	}
}
