package com.trabalho.springmvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AcessLoginRegisterPage extends GenericFilterBean{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if (isAuthenticated() && (servletRequest.getRequestURI().endsWith("/login") || servletRequest.getRequestURI().endsWith("/register"))) {
            String encodedRedirectURL = ((HttpServletResponse) response).encodeRedirectURL(servletRequest.getContextPath() + "/user");
            servletResponse.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
            servletResponse.setHeader("Location", encodedRedirectURL);
        }

        chain.doFilter(servletRequest, servletResponse);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
          isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    
}
