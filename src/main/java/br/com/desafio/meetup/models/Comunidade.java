package br.com.desafio.meetup.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

import br.com.desafio.meetup.daos.EventoDAO;

@Entity
public class Comunidade {
	@Id
	private int id;
	
	private String cidade;
	private String nome;
	@Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
	private String logo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public ComunidadeJson toComunidadeJsonProximoEvento(Comunidade comunidade) {
		ComunidadeJson comunidadeJson = new ComunidadeJson();
		comunidadeJson.setId(comunidade.getId());
		comunidadeJson.setNome(comunidade.getNome());
		comunidadeJson.setLogo(comunidade.getLogo());
		comunidadeJson.setCidade(comunidade.getCidade());
		
		EventoDAO eventoDao = new EventoDAO();
		Evento evento = eventoDao.getProximoEventosByIdComunidade(comunidade.getId());
		if(evento.getId() != 0) {
			List<Evento> eventos = new ArrayList<>();
			eventos.add(evento);
		
			comunidadeJson.setEvento(eventos);
		}
		return comunidadeJson;
	}

	public ComunidadeJson toComunidadeJson(Comunidade comunidade) {
		ComunidadeJson comunidadeJson = new ComunidadeJson();
		comunidadeJson.setId(getId());
		comunidadeJson.setNome(comunidade.getNome());
		comunidadeJson.setLogo(comunidade.getLogo());
		comunidadeJson.setCidade(comunidade.getCidade());
		
		EventoDAO eventoDao = new EventoDAO();
		List<Evento> eventos = eventoDao.getEventosByIdComunidade(comunidade.getId());
		comunidadeJson.setEvento(eventos);
		
		return comunidadeJson;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

}