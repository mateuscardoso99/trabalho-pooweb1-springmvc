package com.trabalho.springmvc.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.repository.UsuarioRepository;

//bibliotecas do hibernate não precisam ser adicionadas no pom, pois o spring-data-jpa já vem com elas
@Repository
public class UsuarioDAO {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}

    public Optional<Usuario> findByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

	public Optional<Usuario> findById(Long id){
        return usuarioRepository.findById(id);
    }

	public void atualizar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	public void deletar(Usuario usuario){
		usuarioRepository.delete(usuario);
	}
}
