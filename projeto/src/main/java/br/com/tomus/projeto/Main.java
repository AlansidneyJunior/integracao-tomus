package br.com.tomus.projeto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.tomus.projeto.config.AppConfig;
import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.models.Aluno;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		AlunoDao alunoDao = (AlunoDao) context.getBean("alunoDao");
		
		Aluno aluno = new Aluno("asdasd", "3132", 25);
		alunoDao.save(aluno);
		
	}
}
