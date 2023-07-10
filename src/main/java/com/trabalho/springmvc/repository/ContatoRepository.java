package com.trabalho.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long>{
    @Query("SELECT c from Contato c WHERE c.usuario.id=:id")
    public List<Contato> findAllByUser(@Param("id") Long id);
}
