package com.trabalho.springmvc.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

public class LinkForm {
    private Long id;

    @NotBlank(message = "URL vazia")
    @URL(message = "URL inválida")
    private String url;

    private String descricao;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
