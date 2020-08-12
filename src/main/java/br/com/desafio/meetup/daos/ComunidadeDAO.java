package br.com.desafio.meetup.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.meetup.models.Comunidade;

@Repository
@Transactional //spring via cuidar da transacao
public class ComunidadeDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Comunidade comunidade) {
		manager.persist(comunidade);
	}

}
