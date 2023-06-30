package com.trabalho.springmvc.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.trabalho.springmvc.model.Documento;

@Repository
public class DocumentoDAO {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

    public List<Documento> findAll(Long idUsuario){
        String sql = "SELECT l from Documento l JOIN l.usuario";
		Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery(sql);
        return query.list();
    }

    public void salvar(Documento documento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(documento);
	}

	public Optional<Documento> findById(Long id){
        Session session = this.sessionFactory.getCurrentSession();		
		Documento documento = (Documento) session.load(Documento.class, id);
		return Optional.ofNullable(documento);
    }

	public void atualizar(Documento documento) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(documento);
	}

	public void deletar(Documento documento){
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(documento);
	}
}
