package com.trabalho.springmvc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalho.springmvc.dao.UsuarioDAO;
import com.trabalho.springmvc.model.Usuario;

import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioDAO usuarioDAO;

	@Transactional
	public Usuario salvar(Usuario u) {
		return this.usuarioDAO.salvar(u);
	}

	@Transactional
	public void atualizar(Usuario u) {
		this.usuarioDAO.atualizar(u);
	}

	// @Transactional
	// public Optional<Usuario> findByEmail(String email) {
	// 	return this.usuarioDAO.findByEmail(email);
	// }

	@Transactional
    public Optional<Usuario> findById(Long id) {
		return this.usuarioDAO.findById(id);
	}

	@Transactional
	public void deletar(Usuario usuario) {
		this.usuarioDAO.deletar(usuario);
	}
}
