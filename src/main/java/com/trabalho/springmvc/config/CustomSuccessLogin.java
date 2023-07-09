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

import com.trabalho.springmvc.dao.UsuarioDAO;
import com.trabalho.springmvc.model.Usuario;

public class CustomSuccessLogin implements AuthenticationSuccessHandler{

    @Autowired
    private UsuarioDAO usuarioDAO;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Optional<Usuario> usuario = usuarioDAO.findByEmail(authentication.getName());
        HttpSession session = request.getSession();
        if(session != null)
            session.setAttribute("usuario", usuario.get());
    }
    
}
