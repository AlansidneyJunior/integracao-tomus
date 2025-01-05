package br.com.tomus.projeto;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.tomus.projeto.config.AppConfig;
import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.dao.NotaDao;
import br.com.tomus.projeto.models.Aluno;
import br.com.tomus.projeto.models.Nota;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		NotaDao notaDao = (NotaDao) context.getBean("notaDao");
		AlunoDao alunoDao = (AlunoDao) context.getBean("alunoDao");
		
		Aluno aluno = alunoDao.findById(1L);
		Nota nota1 = new Nota("teste", "pasda", 7.5, aluno);
		
//		notaDao.save(nota1);
		List<Nota> notas = aluno.getNotas();

		for (Nota nota : notas) {
			
			System.out.println("Data: " + nota.getData());
			System.out.println("Aluno: " + nota.getAluno().getNome());
			System.out.println("Disciplina: " + nota.getDisciplina());
			System.out.println("Professor: " + nota.getProfessor());
			System.out.println("Nota: " + nota.getNota());
		
		}
	}
}