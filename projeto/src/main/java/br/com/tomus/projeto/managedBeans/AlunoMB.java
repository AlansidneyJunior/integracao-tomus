package br.com.tomus.projeto.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.models.Aluno;

@Component("alunoMB")
@SessionScope
public class AlunoMB {

    private Aluno aluno = new Aluno();

    @Autowired
    private AlunoDao alunoDao;

    private List<Aluno> alunos = new ArrayList<>();

    public String save() {
    	if(aluno.getId() == null) {
    		alunoDao.save(aluno);
    		alunos.add(aluno);    		
    	} else {
    		alunoDao.update(aluno);
    	}
    	updateAlunoList();
    	clearForm();
        return null;
    }

    public String remove() {
        alunoDao.remove(aluno.getId());
        updateAlunoList();
        clearForm();
        return null;
    }
    
    public String edit(Aluno selectedAluno) {
    	this.aluno = selectedAluno;
    	return null;
    }
    
    public void clearForm() {
    	aluno = new Aluno();
    }
    
    public void updateAlunoList() {
    	alunos = alunoDao.findAll();
    }

    @PostConstruct
    public void init() {
        updateAlunoList();
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
    
    public String teste() {
    	System.out.println("Teste!");
    	return null;
    }
}
