package br.com.tomus.projeto.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.models.Aluno;

@Component
@ManagedBean(name="alunoMB")
@SessionScope
public class AlunoMB{
	
	private Aluno aluno =  new Aluno();
	
	@Autowired
	private AlunoDao alunoDao;
	
	private List<Aluno> alunos = new ArrayList<>();
	
	public String save() {
		
		alunoDao.save(aluno);
		alunos.add(aluno);
		
		return null;
	}
	
	@PostConstruct
	public void init() {
		alunos = alunoDao.findAll();
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
}
