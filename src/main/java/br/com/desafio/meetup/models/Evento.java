package br.com.desafio.meetup.models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.desafio.meetup.utils.TimeUtils;

@Entity
public class Evento {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String titulo;
	@DateTimeFormat
	private Date data;
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
