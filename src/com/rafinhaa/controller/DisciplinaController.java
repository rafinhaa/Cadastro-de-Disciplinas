package com.rafinhaa.controller;

import java.util.List;

import com.rafinhaa.dao.DisciplinaDAO;
import com.rafinhaa.model.Disciplina;

public class DisciplinaController {

	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public void inserirDisciplina(Disciplina disciplina){
		disciplinaDAO.inserir(disciplina);
	}
	
	public List<Disciplina> listarDisciplinas(){
		return disciplinaDAO.listar();
	}
	
	public void deletar(Disciplina disciplina){
		disciplinaDAO.deletar(disciplina);
	}
	
	public void atualizar(Disciplina disciplina){
		disciplinaDAO.atualizar(disciplina);
	}
	
}
