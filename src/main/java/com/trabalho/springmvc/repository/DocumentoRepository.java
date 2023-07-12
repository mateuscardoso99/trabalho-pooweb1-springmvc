package com.trabalho.springmvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento,Long>{
    @Query("SELECT doc from Documento doc WHERE doc.usuario.id=:id")
    public List<Documento> findAllByUser(@Param("id") Long id);

    @Query("SELECT doc from Documento doc WHERE doc.id=:id AND doc.usuario.id=:user_id")
    public Optional<Documento> find(@Param("id") Long id, @Param("user_id") Long userId);

    @Query("SELECT doc from Documento doc WHERE doc.arquivo=:filename AND doc.usuario.id=:user_id")
    public Optional<Documento> findByFileName(@Param("filename") String file, @Param("user_id") Long userId);
}
