package br.com.tomus.projeto.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import br.com.tomus.projeto.dao.NotaDao;
import br.com.tomus.projeto.models.Nota;

@Component("notaMB")
@SessionScope
public class NotaMB {

    private Nota nota = new Nota();

    @Autowired
    private NotaDao notaDao;

    private List<Nota> notas = new ArrayList<>();

    public String save() {
    	if(nota.getId() == null) {
    		notaDao.save(nota);
    		notas.add(nota);    		
    	} else {
    		notaDao.update(nota);
    		updateNotaList();
    	}
    	clearForm();
        return null;
    }

    public String remove() {
        notaDao.remove(nota.getId());
        updateNotaList();
        clearForm();
        return null;
    }
    
    public String edit(Nota selectedNota) {
    	this.nota = selectedNota;
    	return null;
    }
    
    public void clearForm() {
    	nota = new Nota();
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
    
    public String teste() {
    	System.out.println("Teste!");
    	return null;
    }
}
