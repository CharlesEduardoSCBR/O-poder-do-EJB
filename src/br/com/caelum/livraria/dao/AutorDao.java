package br.com.caelum.livraria.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Autor;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AutorDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostConstruct
	void aposCriacao() {
		System.out.println("[INFO] AutorDao foi criado.");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void salva(Autor autor) {
		manager.persist(autor);
	}

	public List<Autor> todosAutores() {
		return manager.createQuery("SELECT c FROM Autor c", Autor.class).getResultList();
	}

	public Autor buscaPelaId(Integer autorId) {
		return manager.find(Autor.class, autorId);
	}
}
