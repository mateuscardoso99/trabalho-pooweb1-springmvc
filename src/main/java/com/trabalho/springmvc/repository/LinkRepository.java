package com.trabalho.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long>{
    
}
