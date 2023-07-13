package com.trabalho.springmvc.form;

import org.springframework.web.multipart.MultipartFile;

public class AtualizarContatoForm {
    private String id;
    private String nome;
    private String telefone;
    private MultipartFile foto;
    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public MultipartFile getFoto() {
        return foto;
    }
    public void setFoto(MultipartFile foto) {
        this.foto = foto;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
