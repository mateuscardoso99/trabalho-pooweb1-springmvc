package com.trabalho.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Long>{
    @Query("SELECT doc from Documento doc WHERE doc.usuario.id=:id")
    public List<Documento> findAllByUser(@Param("id") Long id);
}
