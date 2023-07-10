package com.trabalho.springmvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long>{
    @Query("SELECT link from Link link WHERE link.usuario.id=:id")
    public List<Link> findAllByUser(@Param("id") Long id);
}
