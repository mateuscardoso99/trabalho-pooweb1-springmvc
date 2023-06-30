package com.trabalho.springmvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documento")
public class Documento {
    @Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String arquivo;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Documento() {}
    
    public Documento(String arquivo) {
        this.arquivo = arquivo;
    }

    public Documento(Long id, String arquivo, Usuario u) {
        this.id = id;
        this.arquivo = arquivo;
        this.usuario = u;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getArquivo() {
        return arquivo;
    }
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario u) {
        this.usuario = u;
    }
}
