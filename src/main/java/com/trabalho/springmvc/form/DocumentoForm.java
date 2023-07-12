package com.trabalho.springmvc.form;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoForm {
    private Long id;
    private MultipartFile arquivo;

    
    public MultipartFile getArquivo() {
        return arquivo;
    }
    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    
}
