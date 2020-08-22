package br.com.desafio.meetup.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Calendar;
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
public class ComunidadeDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void gravar(Comunidade comunidade) {
		manager.persist(comunidade);
	}
	
	public List<String> adicionaRegistrosIniciais(ComunidadeJson comunidade) {
		List<String> erroList = new ArrayList<>();
		erroList.addAll(adicionaComunidadeInicial(comunidade));
		erroList.addAll(adicionaEventoInicial(comunidade));
		
		return erroList;
	}
	
	public List<String> adicionaComunidadeInicial(ComunidadeJson comunidade) {
		List<String> erroList = new ArrayList<>();
        String sql = "INSERT INTO comunidade(id,nome,logo, cidade) VALUES(?,?,?,?)";  

        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetup?useTimezone=true&serverTimezone=UTC", "root", "");
        		PreparedStatement stmt = connection.prepareStatement(sql);) {  
            
            stmt.setInt(1, comunidade.getId());  
            stmt.setString(2, comunidade.getNome());  
            stmt.setString(3, comunidade.getLogo());
            stmt.setString(4, comunidade.getCidade());

            int sucess = stmt.executeUpdate();  
            
            if( sucess == 1) {
            	System.out.println("Inserido comunidade com sucesso");
            }
        } catch (Exception u) {  
            System.out.println(u);
            erroList.add("Erro ao adicionar comunidade " + u.getMessage());
        }
        
        return erroList;
	}
	
	public List<String>  adicionaEventoInicial(ComunidadeJson comunidade){  
		List<String> erroList = new ArrayList<>();
		for(Evento evento : comunidade.getEvento()) {
			
	        String sql = "INSERT INTO evento(id,data,detalhes,titulo,comunidadeId) VALUES(?,?,?,?,?)";  
	
	        try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/meetup?useTimezone=true&serverTimezone=UTC", "root", "");
	        		PreparedStatement stmt = connection.prepareStatement(sql);) {  
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(evento.getData());
	            
	        	
	            stmt.setInt(1, evento.getId());  
	            stmt.setString(2, TimeUtils.calendarDateAsString(cal, "yyyy-MM-dd HH:mm:ss"));  
	            stmt.setString(3, evento.getDetalhes());  
	            stmt.setString(4, evento.getTitulo());
	            stmt.setInt(5, comunidade.getId()); 
	
	            int sucess = stmt.executeUpdate();  
	            
	            if( sucess == 1) {
	            	System.out.println("Inserido evento com sucesso");
	            }
	              
	
	        } catch (Exception u) {  
	        	System.out.println(u);
	            erroList.add("Erro ao adicionar evento " + u.getMessage());
	        }
		}
		return erroList;
    }  
	
	public List<Comunidade> listar() {
		return manager.createQuery("Select d from Comunidade d", Comunidade.class).getResultList();
	}

	public List<Evento> getEventosByComunidade(int comunidadeId) {
		return manager.createQuery("Select e from Evento e Where e.comunidadeId = :id", Evento.class)
				.setParameter("id", comunidadeId)
				.getResultList();
	}

	public Comunidade getComunidadeById(int comunidadeId) {
		try {
			return manager.createQuery("Select c from Comunidade c Where c.id = :id" , Comunidade.class)
					.setParameter("id", comunidadeId)
					.getSingleResult();
		}catch(Exception e){
			System.out.println("Nao foi encontrado nenhuma comunidade, comunidade id: " + comunidadeId );
			return new Comunidade();
		}
	}

	public List<Comunidade> getComunidadeByCidade(String cidade) {
		try {
			return manager.createQuery("Select c from Comunidade c Where c.cidade like '%" + cidade+ "%'", Comunidade.class).getResultList();
		}catch(Exception e){
			System.out.println("Nao foi encontrado nenhuma comunidade, cidade: " + cidade );
			return null;
		}
	}
        

}
