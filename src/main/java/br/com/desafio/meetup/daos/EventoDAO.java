package br.com.desafio.meetup.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.desafio.meetup.models.Comunidade;
import br.com.desafio.meetup.models.ComunidadeJson;
import br.com.desafio.meetup.models.Evento;
import br.com.desafio.meetup.utils.TimeUtils;

@Repository
@Transactional //spring via cuidar da transacao
public class EventoDAO {
	@PersistenceContext
	private EntityManager manager;

	public List<Evento> listar() {
		return manager.createQuery("Select e from Evento e", Evento.class).getResultList();
	}
	
	public void gravar(Evento evento) {
		manager.persist(evento);
	}

	public List<Evento> getEventosByIdComunidade(int comunidadeId) {
		String sql = "Select e.id as id, e.data as data, e.detalhes as detalhes,"
				+ "e.titulo as titulo, e.comunidadeId as comunidadeId from Evento e Where e.comunidadeId = ?";
		List<Evento> eventos = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetup?useTimezone=true&serverTimezone=UTC", "root", "");
        		PreparedStatement stmt = connection.prepareStatement(sql);) {  
            
            stmt.setInt(1, comunidadeId); 

            ResultSet resultSet = stmt.executeQuery();  
            
            while(resultSet.next()) {
            	Evento evento = new Evento();
            	evento.setComunidadeId(resultSet.getInt("comunidadeId"));
            	String data = resultSet.getString("data");
            	Date dataAux = TimeUtils.stringToDate(data, "yyyy-MM-dd HH:mm:ss");
            	evento.setData(dataAux);
            	evento.setDetalhes(resultSet.getString("detalhes"));
            	evento.setId(resultSet.getInt("id"));
            	evento.setTitulo(resultSet.getString("titulo"));
            	
            	eventos.add(evento);
            }
        } catch (Exception u) {  
            System.out.println(u);
        }
        
        return eventos;
	}
	
	public Evento getProximoEventosByIdComunidade(int comunidadeId) {
		Evento evento = new Evento();
		String sql = "Select e.id as id, e.data as data, e.detalhes as detalhes,"
				+ "e.titulo as titulo, e.comunidadeId as comunidadeId from Evento e Where e.comunidadeId = ? "
				+ "and DATE(e.data) > NOW() order by e.data limit 1 ";
        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetup?useTimezone=true&serverTimezone=UTC", "root", "");
        		PreparedStatement stmt = connection.prepareStatement(sql);) {  
            
            stmt.setInt(1, comunidadeId); 

            ResultSet resultSet = stmt.executeQuery();  
            
            while(resultSet.next()) {
            	evento.setComunidadeId(resultSet.getInt("comunidadeId"));
            	String data = resultSet.getString("data");
            	Date dataAux = TimeUtils.stringToDate(data, "yyyy-MM-dd HH:mm:ss");
            	evento.setData(dataAux);
            	evento.setDetalhes(resultSet.getString("detalhes"));
            	evento.setId(resultSet.getInt("id"));
            	evento.setTitulo(resultSet.getString("titulo"));
            	
            }
        } catch (Exception u) {  
            System.out.println(u);
        }
        
        return evento;
	}
	
	
	
	
	public List<String> adicionaComunidadeInicial(ComunidadeJson comunidade) {
		List<String> erroList = new ArrayList<>();
        
        
        return erroList;
	}
}
