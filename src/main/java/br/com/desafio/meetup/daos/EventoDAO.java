package br.com.desafio.meetup.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.meetup.models.Evento;

@Repository
@Transactional //spring via cuidar da transacao
public class EventoDAO {
	@PersistenceContext
	private EntityManager manager;

	public List<Evento> listar() {
		return manager.createQuery("Select e from Evento e", Evento.class).getResultList();
	}
	
}
