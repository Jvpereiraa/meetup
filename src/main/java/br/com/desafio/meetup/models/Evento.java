package br.com.desafio.meetup.models;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Evento {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String titulo;
	@DateTimeFormat
	private Date data;
	@Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
	private String detalhes;
	/*@Lob @Basic(fetch = FetchType.LAZY) @Column(columnDefinition = "text")
	private String logoMeetup;*/

	private int comunidadeId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetalhes() {
		return detalhes;
	}

	public void setDetalhes(String detalhes) {
		this.detalhes = detalhes;
	}

	/*public String getLogoMeetup() {
		return logoMeetup;
	}

	public void setLogoMeetup(String logoMeetup) {
		this.logoMeetup = logoMeetup;
	}
*/
	public int getComunidadeId() {
		return comunidadeId;
	}

	public void setComunidadeId(int comunidadeId) {
		this.comunidadeId = comunidadeId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	

}
