package com.trabalho.springmvc.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomErroLogin implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
      HttpServletRequest request,
      HttpServletResponse response,
      org.springframework.security.core.AuthenticationException exception) 
      throws IOException, ServletException {
        request.getSession().setAttribute("error", "email ou senha incorretos");
        response.sendRedirect("login");
    }
}
