package br.com.tomus.projeto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.tomus.projeto.config.AppConfig;
import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.models.Aluno;
import br.com.tomus.projeto.models.Nota;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Aluno aluno = new Aluno("Pedro2", "3132", 25);
		Nota nota = new Nota("Geografia", "Paulo", 7.8, aluno);
		
		System.out.println("Data: " + nota.getData());
		System.out.println("Aluno: " + nota.getAluno().getNome());
		System.out.println("Disciplina: " + nota.getDisciplina());
		System.out.println("Professor: " + nota.getProfessor());
		System.out.println("Nota: " + nota.getNota());
	}
}