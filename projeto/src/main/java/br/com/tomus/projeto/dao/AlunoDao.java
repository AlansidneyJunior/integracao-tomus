package br.com.tomus.projeto.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import br.com.tomus.projeto.connection.ConnectionFactory;
import br.com.tomus.projeto.models.Aluno;


@Component
public class AlunoDao {
	
	public void  print() {
		System.out.println("oi");
		
	}
	public Aluno save(Aluno aluno) {
		Session session = new ConnectionFactory().getConnection();
		
		try {
			session.getTransaction().begin();
			session.persist(aluno);
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
		return aluno;
	}
	
	public Aluno update(Aluno aluno) {
		Session session = new ConnectionFactory().getConnection();
		
		try {
			Aluno alunoExistente = session.find(Aluno.class, aluno.getId());
			
			if(alunoExistente != null) {
				session.getTransaction().begin();
	            alunoExistente.setNome(aluno.getNome());
	            alunoExistente.setCpf(aluno.getCpf());
	            alunoExistente.setIdade(aluno.getIdade());
	            session.getTransaction().commit();
			}
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return aluno;
        
	}
	
	public Aluno findById(Long id) {
		Session session = new ConnectionFactory().getConnection();
		Aluno alunoEncontrado = null;
		
		try {
			session.getTransaction().begin();
			alunoEncontrado= session.find(Aluno.class, id);
			
			if (alunoEncontrado == null) {
				System.out.println("Aluno com id:" + id + " não foi encontrado!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		return alunoEncontrado;
	}
	
	public List<Aluno> findAll(){
		Session session = new ConnectionFactory().getConnection();
		List<Aluno> alunos = null;
		
		try {
			alunos = session.createQuery("SELECT a FROM Aluno a ORDER BY a.id", Aluno.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return alunos;
	}
	
	public Aluno remove(Long id) {
		Session session = new ConnectionFactory().getConnection();
		Aluno alunoRemovido = null;
		
		try {
			alunoRemovido = session.find(Aluno.class, id);
			
			session.getTransaction().begin();
			session.remove(alunoRemovido);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return alunoRemovido;
	}
}
