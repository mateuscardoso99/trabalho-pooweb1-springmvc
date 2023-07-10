package com.trabalho.springmvc.config;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.trabalho.springmvc.model.Usuario;
import com.trabalho.springmvc.repository.UsuarioRepository;

@Component
public class CustomSuccessLogin implements AuthenticationSuccessHandler{
    @Autowired
	private UsuarioRepository usuarioRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(authentication.getName());
        HttpSession session = request.getSession();
        if(session != null)
            session.setAttribute("usuario", usuario.get());

        response.sendRedirect(request.getContextPath()+"/user");
    }
    
}
