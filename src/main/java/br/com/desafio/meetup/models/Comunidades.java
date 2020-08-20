package br.com.desafio.meetup.models;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class Comunidades {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToMany
	private List<ComunidadeJson> comunidade;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<ComunidadeJson> getComunidade() {
		return comunidade;
	}
	public void setComunidade(List<ComunidadeJson> comunidade) {
		this.comunidade = comunidade;
	}

}
