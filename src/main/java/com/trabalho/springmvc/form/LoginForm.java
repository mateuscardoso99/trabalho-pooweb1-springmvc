package com.trabalho.springmvc.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank(message = "email inválido")
    @Email(message = "email inválido")
    private String email;

    @NotBlank(message = "senha inválida")
    @Size(min = 4, message = "senha deve ter no mínimo 4 caracteres")
    private String senha;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    
}
