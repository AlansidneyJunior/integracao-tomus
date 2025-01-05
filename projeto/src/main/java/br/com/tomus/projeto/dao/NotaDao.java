package br.com.tomus.projeto.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import br.com.tomus.projeto.connection.ConnectionFactory;
import br.com.tomus.projeto.models.Nota;

@Component
public class NotaDao {
	public Nota save(Nota nota) {
		Session session = new ConnectionFactory().getConnection();
		
		try {
			session.getTransaction().begin();
			session.persist(nota);
			session.getTransaction().commit();
		} catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
		return nota;
	}
	
	public Nota update(Nota nota) {
		Session session = new ConnectionFactory().getConnection();
		
		try {
			Nota notaExistente = session.find(Nota.class, nota.getId());
			
			if(notaExistente != null) {
				session.getTransaction().begin();
	            notaExistente.setData(LocalDateTime.now());
	            notaExistente.setDisciplina(nota.getDisciplina());
	            notaExistente.setProfessor(nota.getProfessor());
	            notaExistente.setNota(nota.getNota());
	            notaExistente.setAluno(nota.getAluno());
	            session.getTransaction().commit();
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nota;
        
	}
	
	public Nota findById(Long id) {
		Session session = new ConnectionFactory().getConnection();
		Nota notaEncontrada = null;
		
		try {
			session.getTransaction().begin();
			notaEncontrada= session.find(Nota.class, id);
			
			if (notaEncontrada == null) {
				System.out.println("Nota com id:" + id + " não foi encontrado!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return notaEncontrada;
	}
	
	public List<Nota> findAll(){
		Session session = new ConnectionFactory().getConnection();
		List<Nota> notas = null;
		
		try {
			notas = session.createQuery("SELECT n FROM Nota n ORDER BY n.id", Nota.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notas;
	}
	
	public Nota remove(Long id) {
		Session session = new ConnectionFactory().getConnection();
		Nota notaRemovida = null;
		
		try {
			notaRemovida = session.find(Nota.class, id);
			
			session.getTransaction().begin();
			session.remove(notaRemovida);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return notaRemovida;
	}
}
