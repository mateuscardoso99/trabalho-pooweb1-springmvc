package com.trabalho.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Long>{
    
}
