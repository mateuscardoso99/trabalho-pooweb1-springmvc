package com.trabalho.springmvc.config;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.repository.UsuarioRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.findByEmail(email);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("user not available");
        }
        return new org.springframework.security.core.userdetails.User(
            user.get().getEmail(), 
            user.get().getSenha(), 
            true, 
            true,
            true, 
            true, 
            new ArrayList<>()
        );
	}
}
