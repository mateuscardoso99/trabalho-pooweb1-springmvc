package com.trabalho.springmvc.form;

import org.springframework.web.multipart.MultipartFile;

public class DocumentoForm {
    private MultipartFile arquivo;

    public MultipartFile getArquivo() {
        return arquivo;
    }

    public void setArquivo(MultipartFile arquivo) {
        this.arquivo = arquivo;
    }

    
}
