package com.trabalho.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
    
}
