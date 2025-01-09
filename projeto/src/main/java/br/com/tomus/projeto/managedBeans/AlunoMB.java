package br.com.tomus.projeto.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import br.com.tomus.projeto.dao.AlunoDao;
import br.com.tomus.projeto.models.Aluno;

@Component("alunoMB")
@RequestScope
public class AlunoMB {

    private Aluno aluno = new Aluno();

    @Autowired
    private AlunoDao alunoDao;

    private List<Aluno> alunos = new ArrayList<>();

    public String save() {
    	try {
    		if(aluno.getId() == null) {
        		alunoDao.save(aluno);
        		alunos.add(aluno);
        		
        		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Aluno cadastrado.", "Aluno cadastrado com Sucesso."));
        	} else {
        		alunoDao.update(aluno);
        		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Aluno atualizado.", "Aluno atualizado com Sucesso."));
        	}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO: Aluno NÃO foi cadastrado.", "ERROR: Aluno NÃO foi cadastrado."));
		}
    	updateAlunoList();
    	clearForm();
        return null;
    }

    public String remove() {
    	try {
    		alunoDao.remove(aluno.getId());
    		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Aluno REMOVIDO.", "SUCESSO: Aluno REMOVIDO."));
    	} catch (Exception e) {
    		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO: Aluno NÃO foi REMOVIDO.", "ERROR: Aluno NÃO foi REMOVIDO."));
		}
 
        updateAlunoList();
        clearForm();
        return null;
    }
    
    public String edit(Aluno selectedAluno) {
    	
    	this.aluno = selectedAluno;
    	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"MODO DE EDIÇÃO: Atualize os dados do aluno.", "MODO DE EDIÇÃO: Atualize os dados do aluno."));
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
}
