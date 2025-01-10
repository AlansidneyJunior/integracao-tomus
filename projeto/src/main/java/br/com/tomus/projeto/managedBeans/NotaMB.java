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
import br.com.tomus.projeto.dao.NotaDao;
import br.com.tomus.projeto.models.Aluno;
import br.com.tomus.projeto.models.Nota;

@Component("notaMB")
@RequestScope
public class NotaMB {

    private Nota nota = new Nota();
    
    private Aluno aluno = new Aluno();
    
    @Autowired
    private NotaDao notaDao;
    
    @Autowired
    private AlunoDao alunoDao;

    private List<Nota> notas = new ArrayList<>();
    
    public String save() {
        Aluno alunoPersistido = alunoDao.findById(aluno.getId());
        if (alunoPersistido == null) {
        	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"ERRO: AlunoID NÃO encontrado.", "ERRO: AlunoID NÃO encontrado."));
            throw new IllegalArgumentException("Aluno não encontrado para o ID fornecido.");
        }

        nota.setAluno(alunoPersistido);

        if (nota.getId() == null) {
            notaDao.save(nota);
            notas.add(nota);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Nota cadastrada.", "Nota cadastrada com Sucesso."));
        } else {
            notaDao.update(nota);
            FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Nota atualizada.", "Nota atualizada com Sucesso."));
        }
        updateNotaList();
        clearForm();
        return null;
    }

    public String remove() {
        notaDao.remove(nota.getId());
        updateNotaList();
        FacesContext.getCurrentInstance().addMessage("", new FacesMessage("SUCESSO: Nota REMOVIDA.", "SUCESSO: Nota REMOVIDA."));
        clearForm();
        return null;
    }
    
    public String edit(Nota selectedNota) {
    	this.nota = selectedNota;
    	this.aluno.setId(selectedNota.getAluno().getId());
    	FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"MODO DE EDIÇÃO: Atualize os dados do aluno.", "MODO DE EDIÇÃO: Atualize os dados do aluno."));
    	return null;
    }
    
    public void clearForm() {
    	nota = new Nota();
    	aluno = new Aluno();
    }
    
    public void updateNotaList() {
    	notas = notaDao.findAll();
    }

    @PostConstruct
    public void init() {
        updateNotaList();
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
    
    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
